package me.darthwithap.android.compass.data.data_source.sensor

import kotlinx.coroutines.flow.Flow
import me.darthwithap.android.compass.data.models.CompassReadingDto
import me.darthwithap.android.compass.domain.models.CalibrationState
import me.darthwithap.android.compass.domain.models.NorthType

interface SensorData {
  fun getCompassReadingState(northType: NorthType): Flow<CompassReadingDto>
  fun getCalibrationState(): Flow<CalibrationState>
  fun registerListeners()
  fun unregisterListeners()
  fun closeSensor()
}