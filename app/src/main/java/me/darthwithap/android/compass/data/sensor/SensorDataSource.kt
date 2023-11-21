package me.darthwithap.android.compass.data.sensor

import kotlinx.coroutines.flow.Flow
import me.darthwithap.android.compass.domain.models.CalibrationState

interface SensorDataSource {
    fun getCompassReadingState(): Flow<CompassReadingDto>
    fun getCalibrationState(): Flow<CalibrationState>
    fun registerListeners()
    fun unregisterListeners()
}