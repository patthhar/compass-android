package me.darthwithap.android.compass.domain.repository

import android.location.Location
import kotlinx.coroutines.flow.Flow
import me.darthwithap.android.compass.domain.models.CompassReading


interface CompassRepository {
  fun registerListeners()
  fun unregisterListeners()
  fun getCompassReading(): Flow<CompassReading>
  suspend fun getLastLocation(): Location
}