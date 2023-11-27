package me.darthwithap.android.compass.presentation.compass_readings

import me.darthwithap.android.compass.domain.models.CompassReading
import me.darthwithap.android.compass.util.AppError

data class CompassReadingsState(
    val compassReading: CompassReading? = null,
    val error: AppError? = null
) {

  companion object {
    fun CompassReadingsState.isCompassReadingAvailable() = this.compassReading != null
    fun CompassReadingsState.hasError() = this.error != null
  }
}
