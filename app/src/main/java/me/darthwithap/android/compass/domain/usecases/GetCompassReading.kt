package me.darthwithap.android.compass.domain.usecases

import me.darthwithap.android.compass.domain.repository.CompassRepository

class CompassReadingUseCase(
    private val repository: CompassRepository
) {
    operator fun invoke() {
        repository.getCompassReading()
    }
}