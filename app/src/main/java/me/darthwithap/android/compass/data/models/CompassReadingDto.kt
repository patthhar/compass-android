package me.darthwithap.android.compass.data.models

import me.darthwithap.android.compass.domain.models.CompassReading

data class CompassReadingDto(
    val azimuthInRadians: Float = 0f, // Direction in radians - to point to earth's magnetic north
    val azimuthInDegrees: Float = 0f, // Direction in degrees
    val pitch: Float = 0f, // Vertical tilt (forward and backward)
    val roll: Float = 0f, // Lateral tilt (left to right)
) {
  fun toDomainModel(): CompassReading {
    return CompassReading(azimuthInRadians, azimuthInDegrees, pitch, roll)
  }
}