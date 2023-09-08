package me.darthwithap.android.compass.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.darthwithap.android.compass.data.sensor.SensorDataSource
import me.darthwithap.android.compass.domain.models.CompassReading
import me.darthwithap.android.compass.domain.repository.SensorRepository

class SensorRepositoryImpl(
  private val sensorDataSource: SensorDataSource
) : SensorRepository {
  override fun getCompassReading(): Flow<CompassReading> {
    return sensorDataSource.getCompassReadingState().map {
      it.toDomainModel()
    }
  }
}