package com.example.feature.styleguide

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core.model.StyleGuide
import com.example.core.repository.StyleRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class StyleGuideUiState(
    val guides: List<StyleGuide> = emptyList(),
    val selectedGuide: StyleGuide? = null
)

class StyleGuideViewModel(
    private val repository: StyleRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        StyleGuideUiState(guides = repository.getStyleGuides())
    )
    val uiState: StateFlow<StyleGuideUiState> = _uiState.asStateFlow()

    fun selectGuide(guide: StyleGuide?) {
        _uiState.value = _uiState.value.copy(selectedGuide = guide)
    }
}

class StyleGuideViewModelFactory(
    private val repository: StyleRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return StyleGuideViewModel(repository) as T
    }
}
