package me.darthwithap.android.compass.data.data_source.gps

import android.annotation.SuppressLint
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.tasks.await

class GpsDataSource(
    private val fusedLocationClient: FusedLocationProviderClient
) : GpsData {
    @SuppressLint("MissingPermission")
    override suspend fun getCurrentLocation(): Location {
        fusedLocationClient.lastLocation.await().hasAltitude()
        return fusedLocationClient.lastLocation.await()
    }
}