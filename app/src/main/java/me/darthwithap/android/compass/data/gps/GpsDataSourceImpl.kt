package me.darthwithap.android.compass.data.gps

import android.annotation.SuppressLint
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.tasks.await

class GpsDataSourceImpl(
    private val fusedLocationClient: FusedLocationProviderClient
) : GpsDataSource {
    @SuppressLint("MissingPermission")
    override suspend fun getCurrentLocation(): Location {
        fusedLocationClient.lastLocation.await().hasAltitude()
        return fusedLocationClient.lastLocation.await()
    }
}