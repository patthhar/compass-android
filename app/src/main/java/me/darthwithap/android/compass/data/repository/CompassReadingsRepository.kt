package me.darthwithap.android.compass.data.repository

import android.location.Location
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.darthwithap.android.compass.data.data_source.gps.GpsData
import me.darthwithap.android.compass.data.data_source.sensor.SensorData
import me.darthwithap.android.compass.domain.models.CompassReading
import me.darthwithap.android.compass.domain.repository.CompassRepository

class CompassReadingsRepository(
    private val sensorDataSource: SensorData,
    private val gpsDataSource: GpsData
) : CompassRepository {
    override fun registerListeners() {
        sensorDataSource.registerListeners()
    }

    override fun unregisterListeners() {
        sensorDataSource.unregisterListeners()
    }

    override fun getCompassReading(): Flow<CompassReading> {
        return sensorDataSource.getCompassReadingState().map {
            it.toDomainModel()
        }
    }

    override suspend fun getLastLocation(): Location {
        return gpsDataSource.getCurrentLocation()
    }

    override fun close() {
        sensorDataSource.closeSensor()
    }
}