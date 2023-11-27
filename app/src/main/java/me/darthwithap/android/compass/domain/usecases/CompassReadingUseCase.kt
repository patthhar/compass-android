package me.darthwithap.android.compass.domain.usecases

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import me.darthwithap.android.compass.domain.models.CompassReading
import me.darthwithap.android.compass.domain.repository.CompassRepository
import me.darthwithap.android.compass.util.AppException
import me.darthwithap.android.compass.util.AppResult

class CompassReadingUseCase(
    private val repository: CompassRepository
) {
    suspend operator fun invoke(): Flow<AppResult<CompassReading>> {
        return flow {
            try {
                repository.getCompassReading().collect {
                    emit(AppResult.Success(it))
                }
            } catch (e: AppException) {
                emit(AppResult.Error(e))
            }
        }
    }
}