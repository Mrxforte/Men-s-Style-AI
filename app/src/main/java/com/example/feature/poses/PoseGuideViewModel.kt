package com.example.feature.poses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core.model.PoseGuide
import com.example.core.repository.StyleRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class PoseGuideUiState(
    val selectedCategory: String = "All",
    val poses: List<PoseGuide> = emptyList(),
    val filteredPoses: List<PoseGuide> = emptyList(),
    val categories: List<String> = listOf("All", "Standing", "Walking", "Leaning", "Seated", "Portrait", "Formal")
)

class PoseGuideViewModel(
    private val repository: StyleRepository
) : ViewModel() {

    private val allPoses = repository.getPoseGuides()
    private val _uiState = MutableStateFlow(
        PoseGuideUiState(poses = allPoses, filteredPoses = allPoses)
    )
    val uiState: StateFlow<PoseGuideUiState> = _uiState.asStateFlow()

    fun selectCategory(cat: String) {
        val filtered = repository.getPoseGuides(cat)
        _uiState.value = _uiState.value.copy(
            selectedCategory = cat,
            filteredPoses = filtered
        )
    }
}

class PoseGuideViewModelFactory(
    private val repository: StyleRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PoseGuideViewModel(repository) as T
    }
}
