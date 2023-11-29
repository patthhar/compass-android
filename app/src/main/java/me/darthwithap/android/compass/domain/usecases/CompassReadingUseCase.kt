package me.darthwithap.android.compass.domain.usecases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.darthwithap.android.compass.domain.models.CompassReading
import me.darthwithap.android.compass.domain.models.ReadingDirection
import me.darthwithap.android.compass.domain.repository.CompassRepository
import me.darthwithap.android.compass.util.AppException
import me.darthwithap.android.compass.util.AppResult

class CompassReadingUseCase(
  private val repository: CompassRepository
) {

  private var lastNormalizedDegreeInt: Int? = null

  suspend operator fun invoke(): Flow<AppResult<CompassReading>> {
    return flow {
      try {
        repository.getCompassReading().collect { rawCompassReading ->
          val compassReading = rawCompassReading.copy(
            direction = findDirection(rawCompassReading.azimuthInDegrees),
            normalizedDegrees = normalizeDegrees(rawCompassReading.azimuthInDegrees)
          )

          if (lastNormalizedDegreeInt == null || compassReading.normalizedDegrees.toInt() != lastNormalizedDegreeInt) {
            emit(AppResult.Success(compassReading))
            lastNormalizedDegreeInt = compassReading.normalizedDegrees.toInt()
          }
        }
      } catch (e: AppException) {
        emit(AppResult.Error(e))
      }
    }
  }

  private fun normalizeDegrees(degrees: Float): Float = (degrees % 360 + 360) % 360

  private fun findDirection(degrees: Float): ReadingDirection {
    // Ensuring that azimuthDegrees is within the range [0, 360)
    val normalized = normalizeDegrees(degrees)

    return when {
      normalized >= 337.5 || normalized < 22.5 -> ReadingDirection.North
      normalized >= 22.5 && normalized < 67.5 -> ReadingDirection.NorthEast
      normalized >= 67.5 && normalized < 112.5 -> ReadingDirection.East
      normalized >= 112.5 && normalized < 157.5 -> ReadingDirection.SouthEast
      normalized >= 157.5 && normalized < 202.5 -> ReadingDirection.South
      normalized >= 202.5 && normalized < 247.5 -> ReadingDirection.SouthWest
      normalized >= 247.5 && normalized < 292.5 -> ReadingDirection.West
      normalized >= 292.5 && normalized < 337.5 -> ReadingDirection.NorthWest
      else -> ReadingDirection.UnknownDirection
    }
  }
}
