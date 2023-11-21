package me.darthwithap.android.compass.data.data_source.gps

import android.location.Location

interface GpsData {
  suspend fun getCurrentLocation(): Location
}