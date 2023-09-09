package me.darthwithap.android.compass.data.gps

import android.annotation.SuppressLint
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.tasks.await

class GpsDataSourceImpl(
  private val fusedLocationClient: FusedLocationProviderClient
) : GpsDataSource {
  @SuppressLint("MissingPermission")
  override suspend fun getCurrentLocation(): Pair<Double, Double>? {
    return fusedLocationClient.lastLocation.await()?.let {
      Pair(it.latitude, it.longitude)
    }
  }
}