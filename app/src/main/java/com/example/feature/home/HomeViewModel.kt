package com.example.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.core.model.ClothingCategory
import com.example.core.model.ColorCombination
import com.example.core.model.Outfit
import com.example.core.model.PoseGuide
import com.example.core.model.StyleGuide
import com.example.core.repository.StyleRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class HomeUiState(
    val featuredOutfit: Outfit? = null,
    val trendingCombos: List<ColorCombination> = emptyList(),
    val curatedOutfits: List<Outfit> = emptyList(),
    val styleGuides: List<StyleGuide> = emptyList(),
    val featuredPose: PoseGuide? = null,
    val savedCount: Int = 0,
    val isLoading: Boolean = false
)

class HomeViewModel(
    private val repository: StyleRepository
) : ViewModel() {

    val uiState: StateFlow<HomeUiState> = combine(
        repository.getOutfitsStream(),
        repository.getColorCombinationsStream(),
        repository.getSavedOutfitsStream()
    ) { outfits, combos, savedOutfits ->
        HomeUiState(
            featuredOutfit = outfits.firstOrNull(),
            trendingCombos = combos.take(4),
            curatedOutfits = outfits.drop(1).take(5),
            styleGuides = repository.getStyleGuides().take(3),
            featuredPose = repository.getPoseGuides().firstOrNull(),
            savedCount = savedOutfits.size,
            isLoading = false
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = HomeUiState(isLoading = true)
    )

    fun toggleFavorite(outfit: Outfit) {
        viewModelScope.launch {
            repository.toggleFavorite(outfit.id, "OUTFIT")
        }
    }
}

class HomeViewModelFactory(
    private val repository: StyleRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(repository) as T
    }
}
