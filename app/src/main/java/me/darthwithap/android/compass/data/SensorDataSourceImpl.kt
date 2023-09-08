package me.darthwithap.android.compass.data

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import me.darthwithap.android.compass.data.sensor.CompassReadingDto
import me.darthwithap.android.compass.data.sensor.SensorDataSource

class SensorDataSourceImpl(
  context: Context
) : SensorDataSource {

  private val compassReadingFlow: MutableSharedFlow<CompassReadingDto> = MutableSharedFlow()

  private val sensorManager: SensorManager =
    context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

  private val magnetometer: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
  private var accelerometer: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

  private var geomagneticValues: FloatArray = FloatArray(3)
  private var gravityValues: FloatArray = FloatArray(3)

  private val rotationMatrix = FloatArray(9)
  private val inclinationMatrix = FloatArray(9)
  private val orientationAngles = FloatArray(3)

  private val sensorEventListener = object : SensorEventListener {
    override fun onSensorChanged(event: SensorEvent?) {
      when (event?.sensor?.type) {
        Sensor.TYPE_MAGNETIC_FIELD -> {
          System.arraycopy(event.values, 0, geomagneticValues, 0, event.values.size)
        }

        Sensor.TYPE_ACCELEROMETER -> {
          System.arraycopy(event.values, 0, gravityValues, 0, event.values.size)
        }
      }
      val success = SensorManager.getRotationMatrix(
        rotationMatrix, inclinationMatrix,
        gravityValues, geomagneticValues
      )

      if (success) {
        SensorManager.getOrientation(rotationMatrix, orientationAngles)
      }
      val azimuthInRadians = orientationAngles[0].toDouble()
      val azimuthInDegrees = Math.toDegrees(azimuthInRadians)
      val pitch = Math.toDegrees(orientationAngles[1].toDouble())
      val roll = Math.toDegrees(orientationAngles[2].toDouble())

      val newReading = CompassReadingDto(
        azimuthInRadians = azimuthInRadians.toFloat(),
        azimuthInDegrees = azimuthInDegrees.toFloat(),
        pitch = pitch.toFloat(),
        roll = roll.toFloat()
      )
      compassReadingFlow.tryEmit(newReading)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
      // TODO handle what to do if accuracy changes
    }

  }

  override fun getCompassReadingState(): Flow<CompassReadingDto> {
    return compassReadingFlow.asSharedFlow()
  }

  override fun registerListeners() {
    accelerometer?.also {
      sensorManager.registerListener(sensorEventListener, it, SensorManager.SENSOR_DELAY_NORMAL)
    }
    magnetometer?.also {
      sensorManager.registerListener(sensorEventListener, it, SensorManager.SENSOR_DELAY_NORMAL)
    }
  }

  override fun unregisterListeners() {
    sensorManager.unregisterListener(sensorEventListener)
  }
}