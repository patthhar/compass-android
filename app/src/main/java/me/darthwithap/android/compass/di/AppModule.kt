package me.darthwithap.android.compass.di

import android.app.Application
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.darthwithap.android.compass.data.data_source.gps.GpsData
import me.darthwithap.android.compass.data.data_source.gps.GpsDataSource
import me.darthwithap.android.compass.data.data_source.sensor.SensorData
import me.darthwithap.android.compass.data.data_source.sensor.SensorDataSource
import me.darthwithap.android.compass.data.repository.CompassReadingsRepository
import me.darthwithap.android.compass.domain.repository.CompassRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

  @Provides
  @Singleton
  fun providesFusedLocationProviderClient(
      app: Application
  ): FusedLocationProviderClient {
    return LocationServices.getFusedLocationProviderClient(app.applicationContext)
  }

  @Provides
  @Singleton
  fun providesSensorDataSource(
      app: Application
  ): SensorData {
    return SensorDataSource(app.applicationContext)
  }

  @Provides
  @Singleton
  fun providesGpsDataSource(
      fusedLocationClient: FusedLocationProviderClient
  ): GpsData {
    return GpsDataSource(fusedLocationClient)
  }

  @Provides
  @Singleton
  fun provideCompassRepository(
      sensorDataSource: SensorData,
      gpsDataSource: GpsData
  ): CompassRepository {
    return CompassReadingsRepository(
        sensorDataSource, gpsDataSource
    )
  }
}