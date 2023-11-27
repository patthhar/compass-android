package me.darthwithap.android.compass.presentation.compass_readings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import me.darthwithap.android.compass.domain.repository.CompassRepository
import me.darthwithap.android.compass.domain.usecases.CompassReadingUseCase
import me.darthwithap.android.compass.util.AppError
import me.darthwithap.android.compass.util.AppResult
import javax.inject.Inject

@HiltViewModel
class CompassReadingsViewModel @Inject constructor(
    private val getCompassReading: CompassReadingUseCase,
    private val compassRepository: CompassRepository
) : ViewModel() {
    var state by mutableStateOf(CompassReadingsState())
        private set

    init {
        compassRepository.registerListeners()
        onEvent(CompassReadingEvent.GetCompassReadings)
    }

    override fun onCleared() {
        compassRepository.unregisterListeners()
        compassRepository.close()
        super.onCleared()
    }

    fun onEvent(event: CompassReadingEvent) {
        when (event) {
            CompassReadingEvent.GetCompassReadings -> {
                viewModelScope.launch {
                    handleUseCase(getCompassReading()) {
                        state = state.copy(compassReading = it)
                    }
                }
            }
        }
    }

    private suspend fun <T> handleUseCase(
        usecaseFlow: Flow<AppResult<T>>,
        onError: (AppError?) -> Unit = {},
        onSuccess: (T) -> Unit = {}
    ) {
        usecaseFlow.collect { appResult ->
            when (appResult) {
                is AppResult.Error -> {
                    val error = appResult.error?.error
                    onError(error)
                    state = state.copy(error = error)
                }

                is AppResult.Success -> {
                    onSuccess(appResult.data!!)
                }
            }
        }
    }
}