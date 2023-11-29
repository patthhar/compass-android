package me.darthwithap.android.compass.domain.repository

import android.location.Location
import kotlinx.coroutines.flow.Flow
import me.darthwithap.android.compass.domain.models.CompassReading
import me.darthwithap.android.compass.domain.models.NorthType


interface CompassRepository {
  fun registerListeners()
  fun unregisterListeners()
  fun getCompassReading(northType: NorthType): Flow<CompassReading>
  suspend fun getLastLocation(): Location
  fun close()
}