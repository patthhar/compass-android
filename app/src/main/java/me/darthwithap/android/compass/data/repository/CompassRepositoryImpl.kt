package me.darthwithap.android.compass.data.repository

import android.location.Location
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.darthwithap.android.compass.data.gps.GpsDataSource
import me.darthwithap.android.compass.data.sensor.SensorDataSource
import me.darthwithap.android.compass.domain.models.CompassReading
import me.darthwithap.android.compass.domain.repository.CompassRepository

class CompassRepositoryImpl(
    private val sensorDataSource: SensorDataSource,
    private val gpsDataSource: GpsDataSource
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
}