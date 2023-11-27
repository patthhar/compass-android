package me.darthwithap.android.compass.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import me.darthwithap.android.compass.domain.repository.CompassRepository
import me.darthwithap.android.compass.domain.usecases.CompassReadingUseCase

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {
  @Provides
  @ViewModelScoped
  fun provideCompassReadingUseCase(
      repository: CompassRepository
  ): CompassReadingUseCase = CompassReadingUseCase(repository)
}