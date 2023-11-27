package me.darthwithap.android.compass.presentation.compass_readings

sealed class CompassReadingEvent {
    object GetCompassReadings : CompassReadingEvent()
}