package me.darthwithap.android.compass.data.data_source.sensor

import kotlinx.coroutines.flow.Flow
import me.darthwithap.android.compass.data.models.CompassReadingDto
import me.darthwithap.android.compass.domain.models.CalibrationState

interface SensorData {
    fun getCompassReadingState(): Flow<CompassReadingDto>
    fun getCalibrationState(): Flow<CalibrationState>
    fun registerListeners()
    fun unregisterListeners()
    fun closeSensor()
}