package me.darthwithap.android.compass.data.gps

interface GpsDataSource {
  suspend fun getCurrentLocation(): Pair<Double, Double>?
}