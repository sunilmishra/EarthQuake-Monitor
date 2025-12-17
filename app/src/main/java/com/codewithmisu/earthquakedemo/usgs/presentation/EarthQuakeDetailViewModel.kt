package com.codewithmisu.earthquakedemo.usgs.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codewithmisu.earthquakedemo.core.UiState
import com.codewithmisu.earthquakedemo.usgs.domain.EarthQuakeRepository
import com.codewithmisu.earthquakedemo.usgs.domain.Feature
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EarthQuakeDetailViewModel @Inject constructor(
    val repository: EarthQuakeRepository
) : ViewModel() {

    // State for the UI
    private val _uiState = MutableStateFlow<UiState<Feature>>(UiState.Idle)
    val uiState = _uiState.asStateFlow()

    fun loadEarthQuakeFeature(id: String) {
        viewModelScope.launch {
            try {
                _uiState.emit(UiState.Loading)
                val data = repository.getEarthQuakeInfo()

                val feature = data.features.find { it.id == id }
                feature?.let {
                    _uiState.emit(UiState.Success(feature))
                }
            } catch (e: Exception) {
                _uiState.emit(
                    UiState.Failure(
                        message = e.message ?: "Failed to load earthquake detail data...",
                        throwable = e.cause
                    )
                )
            }
        }
    }
}