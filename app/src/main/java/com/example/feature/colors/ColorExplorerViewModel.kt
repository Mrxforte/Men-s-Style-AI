package com.example.feature.colors

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.core.model.ColorCombination
import com.example.core.repository.StyleRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

data class ColorExplorerUiState(
    val selectedMainColorHex: String? = null,
    val combinations: List<ColorCombination> = emptyList(),
    val availableColors: List<Pair<String, String>> = listOf(
        "All" to "",
        "Navy" to "#0F172A",
        "Cream" to "#FAF8F5",
        "Olive" to "#4A5540",
        "Charcoal" to "#18181B",
        "Sky Blue" to "#93C5FD",
        "Camel" to "#C4A482",
        "White" to "#FFFFFF"
    )
)

class ColorExplorerViewModel(
    private val repository: StyleRepository
) : ViewModel() {

    private val _selectedColor = MutableStateFlow<String?>(null)

    val uiState: StateFlow<ColorExplorerUiState> = combine(
        repository.getColorCombinationsStream(),
        _selectedColor
    ) { allCombos, selectedHex ->
        val filtered = if (selectedHex.isNullOrBlank()) {
            allCombos
        } else {
            allCombos.filter {
                it.primaryHex.equals(selectedHex, ignoreCase = true) ||
                it.secondaryHex.equals(selectedHex, ignoreCase = true) ||
                it.accentHex.equals(selectedHex, ignoreCase = true)
            }
        }

        ColorExplorerUiState(
            selectedMainColorHex = selectedHex,
            combinations = filtered
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ColorExplorerUiState()
    )

    fun selectColor(hex: String?) {
        _selectedColor.value = if (hex.isNullOrBlank()) null else hex
    }
}

class ColorExplorerViewModelFactory(
    private val repository: StyleRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ColorExplorerViewModel(repository) as T
    }
}
