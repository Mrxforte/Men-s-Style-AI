package com.example.feature.outfits

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.core.data.SeedData
import com.example.core.domain.LockedOutfitSlots
import com.example.core.model.ClothingCategory
import com.example.core.model.ClothingItem
import com.example.core.model.Outfit
import com.example.core.model.Season
import com.example.core.repository.StyleRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class OutfitsUiState(
    val selectedTab: Int = 0, // 0: Curated Catalog, 1: Outfit Generator
    val catalogOutfits: List<Outfit> = emptyList(),
    val filteredCatalog: List<Outfit> = emptyList(),
    val selectedOccasion: String = "All",
    val selectedStyle: String = "All",
    val searchQuery: String = "",
    // Generator State
    val generatedOutfit: Outfit? = null,
    val lockedSlots: LockedOutfitSlots = LockedOutfitSlots(),
    val generatorOccasion: String = "Everyday",
    val generatorStyle: String = "All",
    val generatorSeason: Season = Season.ALL_SEASON,
    val excludedColors: Set<String> = emptySet(),
    val isSavedSuccess: Boolean = false,
    val availableColors: List<Pair<String, String>> = listOf(
        "Black" to "#18181B",
        "White" to "#FFFFFF",
        "Navy" to "#0F172A",
        "Grey" to "#64748B",
        "Olive" to "#4A5540",
        "Beige" to "#D4B996",
        "Camel" to "#C4A482"
    )
)

class OutfitsViewModel(
    private val repository: StyleRepository
) : ViewModel() {

    private val _filterState = MutableStateFlow(
        Triple("All" /* occasion */, "All" /* style */, "" /* query */)
    )

    private val _generatorState = MutableStateFlow(
        OutfitsUiState()
    )

    val uiState: StateFlow<OutfitsUiState> = combine(
        repository.getOutfitsStream(),
        _filterState,
        _generatorState
    ) { catalog, filters, genState ->
        val (occ, style, query) = filters
        val filtered = catalog.filter { outfit ->
            (occ == "All" || outfit.occasion.contains(occ, ignoreCase = true)) &&
            (style == "All" || outfit.styleCategory.equals(style, ignoreCase = true)) &&
            (query.isBlank() || outfit.title.contains(query, ignoreCase = true) ||
             outfit.allPieces.any { it.name.contains(query, ignoreCase = true) || it.colorName.contains(query, ignoreCase = true) })
        }

        val currentGenerated = genState.generatedOutfit ?: repository.generateOutfit()

        genState.copy(
            catalogOutfits = catalog,
            filteredCatalog = filtered,
            selectedOccasion = occ,
            selectedStyle = style,
            searchQuery = query,
            generatedOutfit = currentGenerated
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = OutfitsUiState()
    )

    fun selectTab(tab: Int) {
        _generatorState.update { it.copy(selectedTab = tab) }
    }

    fun setCatalogFilters(occasion: String, style: String, query: String) {
        _filterState.value = Triple(occasion, style, query)
    }

    fun toggleFavorite(outfit: Outfit) {
        viewModelScope.launch {
            repository.toggleFavorite(outfit.id, "OUTFIT")
        }
    }

    // Generator Actions
    fun setGeneratorOccasion(occasion: String) {
        _generatorState.update { it.copy(generatorOccasion = occasion) }
        regenerateOutfit()
    }

    fun setGeneratorStyle(style: String) {
        _generatorState.update { it.copy(generatorStyle = style) }
        regenerateOutfit()
    }

    fun toggleColorExclusion(colorHex: String) {
        _generatorState.update { current ->
            val updated = if (current.excludedColors.contains(colorHex)) {
                current.excludedColors - colorHex
            } else {
                current.excludedColors + colorHex
            }
            current.copy(excludedColors = updated)
        }
        regenerateOutfit()
    }

    fun toggleLockTop() {
        val cur = _generatorState.value
        val outfit = cur.generatedOutfit ?: return
        val newLocked = cur.lockedSlots.copy(
            top = if (cur.lockedSlots.top != null) null else outfit.top
        )
        _generatorState.update { it.copy(lockedSlots = newLocked) }
    }

    fun toggleLockBottom() {
        val cur = _generatorState.value
        val outfit = cur.generatedOutfit ?: return
        val newLocked = cur.lockedSlots.copy(
            bottom = if (cur.lockedSlots.bottom != null) null else outfit.bottom
        )
        _generatorState.update { it.copy(lockedSlots = newLocked) }
    }

    fun toggleLockShoes() {
        val cur = _generatorState.value
        val outfit = cur.generatedOutfit ?: return
        val newLocked = cur.lockedSlots.copy(
            shoes = if (cur.lockedSlots.shoes != null) null else outfit.shoes
        )
        _generatorState.update { it.copy(lockedSlots = newLocked) }
    }

    fun toggleLockOuterwear() {
        val cur = _generatorState.value
        val outfit = cur.generatedOutfit ?: return
        val newLocked = cur.lockedSlots.copy(
            outerwear = if (cur.lockedSlots.outerwear != null) null else outfit.outerwear
        )
        _generatorState.update { it.copy(lockedSlots = newLocked) }
    }

    fun regenerateOutfit() {
        val cur = _generatorState.value
        val fresh = repository.generateOutfit(
            styleCategory = cur.generatorStyle,
            occasion = cur.generatorOccasion,
            season = cur.generatorSeason,
            excludedColorHexes = cur.excludedColors,
            lockedSlots = cur.lockedSlots
        )
        _generatorState.update { it.copy(generatedOutfit = fresh, isSavedSuccess = false) }
    }

    fun saveCurrentGeneratedOutfit() {
        val outfit = _generatorState.value.generatedOutfit ?: return
        viewModelScope.launch {
            repository.saveOutfit(outfit)
            _generatorState.update { it.copy(isSavedSuccess = true) }
        }
    }
}

class OutfitsViewModelFactory(
    private val repository: StyleRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return OutfitsViewModel(repository) as T
    }
}
