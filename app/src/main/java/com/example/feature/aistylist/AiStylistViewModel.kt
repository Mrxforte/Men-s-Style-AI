package com.example.feature.aistylist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.core.model.Outfit
import com.example.core.model.StylistAdvice
import com.example.core.model.StylistConsultationRequest
import com.example.core.repository.StyleRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class AiStylistUiState(
    val occasion: String = "Date Night",
    val weather: String = "Mild & Breezy",
    val preferredStyle: String = "Smart Casual",
    val budgetLevel: String = "Balanced",
    val isConsulting: Boolean = false,
    val advice: StylistAdvice? = null
)

class AiStylistViewModel(
    private val repository: StyleRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AiStylistUiState())
    val uiState: StateFlow<AiStylistUiState> = _uiState.asStateFlow()

    init {
        // Run initial consultation
        runConsultation()
    }

    fun setOccasion(occ: String) {
        _uiState.update { it.copy(occasion = occ) }
    }

    fun setWeather(weather: String) {
        _uiState.update { it.copy(weather = weather) }
    }

    fun setPreferredStyle(style: String) {
        _uiState.update { it.copy(preferredStyle = style) }
    }

    fun runConsultation() {
        val current = _uiState.value
        _uiState.update { it.copy(isConsulting = true) }
        viewModelScope.launch {
            val req = StylistConsultationRequest(
                occasion = current.occasion,
                weather = current.weather,
                preferredStyle = current.preferredStyle,
                preferredColors = emptyList(),
                ownedGarmentTypes = emptyList(),
                budgetLevel = current.budgetLevel
            )
            val result = repository.consultStylist(req)
            _uiState.update { it.copy(isConsulting = false, advice = result) }
        }
    }

    fun saveOutfit(outfit: Outfit) {
        viewModelScope.launch {
            repository.saveOutfit(outfit)
        }
    }
}

class AiStylistViewModelFactory(
    private val repository: StyleRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AiStylistViewModel(repository) as T
    }
}
