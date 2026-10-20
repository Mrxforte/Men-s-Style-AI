package com.example.feature.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.core.model.ClothingItem
import com.example.core.model.Outfit
import com.example.core.repository.StyleRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class FavoritesUiState(
    val selectedTab: Int = 0, // 0: Saved Outfits, 1: Favorited Garments
    val savedOutfits: List<Outfit> = emptyList(),
    val favoritedOutfits: List<Outfit> = emptyList(),
    val favoritedItems: List<ClothingItem> = emptyList()
)

class FavoritesViewModel(
    private val repository: StyleRepository
) : ViewModel() {

    private val _tabState = kotlinx.coroutines.flow.MutableStateFlow(0)

    val uiState: StateFlow<FavoritesUiState> = combine(
        repository.getSavedOutfitsStream(),
        repository.getOutfitsStream(),
        repository.getClothingItemsStream(),
        _tabState
    ) { saved, allOutfits, allItems, tab ->
        FavoritesUiState(
            selectedTab = tab,
            savedOutfits = saved,
            favoritedOutfits = allOutfits.filter { it.isFavorite },
            favoritedItems = allItems.filter { it.isFavorite }
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = FavoritesUiState()
    )

    fun selectTab(tab: Int) {
        _tabState.value = tab
    }

    fun removeSavedOutfit(outfitId: String) {
        viewModelScope.launch {
            repository.deleteSavedOutfit(outfitId)
        }
    }

    fun toggleFavoriteItem(item: ClothingItem) {
        viewModelScope.launch {
            repository.toggleFavorite(item.id, "CLOTHING_ITEM")
        }
    }

    fun toggleFavoriteOutfit(outfit: Outfit) {
        viewModelScope.launch {
            repository.toggleFavorite(outfit.id, "OUTFIT")
        }
    }
}

class FavoritesViewModelFactory(
    private val repository: StyleRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FavoritesViewModel(repository) as T
    }
}
