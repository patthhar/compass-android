package me.darthwithap.android.compass.data.sensor

import kotlinx.coroutines.flow.Flow

interface SensorDataSource {
  fun getCompassReadingState(): Flow<CompassReadingDto>
  fun registerListeners()
  fun unregisterListeners()
}