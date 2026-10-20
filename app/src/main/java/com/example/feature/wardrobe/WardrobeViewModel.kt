package com.example.feature.wardrobe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.core.model.ClothingCategory
import com.example.core.model.ClothingItem
import com.example.core.model.FormalityLevel
import com.example.core.repository.StyleRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class WardrobeUiState(
    val selectedCategory: ClothingCategory? = null,
    val selectedFormality: FormalityLevel? = null,
    val searchQuery: String = "",
    val items: List<ClothingItem> = emptyList(),
    val filteredItems: List<ClothingItem> = emptyList(),
    val selectedItemDetail: ClothingItem? = null
)

class WardrobeViewModel(
    private val repository: StyleRepository
) : ViewModel() {

    private val _categoryState = MutableStateFlow<ClothingCategory?>(null)
    private val _formalityState = MutableStateFlow<FormalityLevel?>(null)
    private val _queryState = MutableStateFlow("")
    private val _selectedItemDetail = MutableStateFlow<ClothingItem?>(null)

    val uiState: StateFlow<WardrobeUiState> = combine(
        repository.getClothingItemsStream(),
        _categoryState,
        _formalityState,
        _queryState,
        _selectedItemDetail
    ) { allItems, cat, formality, query, detail ->
        val filtered = allItems.filter { item ->
            (cat == null || item.category == cat) &&
            (formality == null || item.formality == formality) &&
            (query.isBlank() || item.name.contains(query, ignoreCase = true) ||
             item.subcategory.contains(query, ignoreCase = true) ||
             item.material.contains(query, ignoreCase = true) ||
             item.colorName.contains(query, ignoreCase = true))
        }

        WardrobeUiState(
            selectedCategory = cat,
            selectedFormality = formality,
            searchQuery = query,
            items = allItems,
            filteredItems = filtered,
            selectedItemDetail = detail
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = WardrobeUiState()
    )

    fun selectCategory(category: ClothingCategory?) {
        _categoryState.value = category
    }

    fun selectFormality(formality: FormalityLevel?) {
        _formalityState.value = formality
    }

    fun updateSearchQuery(query: String) {
        _queryState.value = query
    }

    fun showItemDetail(item: ClothingItem?) {
        _selectedItemDetail.value = item
    }

    fun toggleFavorite(item: ClothingItem) {
        viewModelScope.launch {
            repository.toggleFavorite(item.id, "CLOTHING_ITEM")
        }
    }
}

class WardrobeViewModelFactory(
    private val repository: StyleRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return WardrobeViewModel(repository) as T
    }
}
