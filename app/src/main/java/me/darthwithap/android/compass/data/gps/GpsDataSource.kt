package me.darthwithap.android.compass.data.gps

import android.location.Location

interface GpsDataSource {
  suspend fun getCurrentLocation(): Location
}