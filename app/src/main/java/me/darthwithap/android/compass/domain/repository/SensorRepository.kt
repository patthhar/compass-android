package me.darthwithap.android.compass.domain.repository

import kotlinx.coroutines.flow.Flow
import me.darthwithap.android.compass.domain.models.CompassReading


interface SensorRepository {
  fun getCompassReading(): Flow<CompassReading>
}