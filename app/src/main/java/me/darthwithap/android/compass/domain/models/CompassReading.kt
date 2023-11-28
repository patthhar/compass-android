package me.darthwithap.android.compass.domain.models

data class CompassReading(
    val azimuthInRadians: Float = 0f,
    val azimuthInDegrees: Float = 0f,
    val pitch: Float = 0f,
    val roll: Float = 0f,
    val direction: ReadingDirection = ReadingDirection.UnknownDirection,
    val normalizedDegrees: Float = 0f
)