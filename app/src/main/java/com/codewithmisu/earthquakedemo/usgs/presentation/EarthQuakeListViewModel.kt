package com.codewithmisu.earthquakedemo.usgs.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codewithmisu.earthquakedemo.core.UiState
import com.codewithmisu.earthquakedemo.usgs.domain.EarthQuakeRepository
import com.codewithmisu.earthquakedemo.usgs.domain.Earthquake
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EarthQuakeListViewModel @Inject constructor(
    val repository: EarthQuakeRepository
) : ViewModel() {

    // State for the UI
    private val _uiState = MutableStateFlow<UiState<Earthquake>>(UiState.Idle)
    val uiState = _uiState.asStateFlow()

    // State for the pull-to-refresh indicator
    private val _pullToRefreshState = MutableStateFlow(false)
    val pullToRefreshState = _pullToRefreshState.asStateFlow()

    init {
        loadEarthQuakeData()
    }

    fun loadEarthQuakeData(forceRefresh: Boolean = false) {
        viewModelScope.launch {
            try {
                if (!forceRefresh) {
                    _uiState.emit(UiState.Loading)
                } else {
                    _pullToRefreshState.emit(true)
                }
                val data = repository.getEarthQuakeInfo(forceRefresh)
                _uiState.emit(UiState.Success(data))
            } catch (e: Exception) {
                _uiState.emit(
                    UiState.Failure(
                        message = e.message ?: "Failed to load earthquake data...",
                        throwable = e.cause
                    )
                )
            } finally {
                _pullToRefreshState.emit(false)
            }
        }
    }
}