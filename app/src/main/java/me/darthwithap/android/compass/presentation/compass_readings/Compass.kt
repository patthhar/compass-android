package me.darthwithap.android.compass.presentation.compass_readings

import android.graphics.Color
import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import me.darthwithap.android.compass.ui.theme.dialColor
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun Compass(
  modifier: Modifier = Modifier,
  style: CompassStyleOptions = CompassStyleOptions(),
  azimuth: Float = 0f
) {
  val radius = style.radius
  val innerRadius = radius - style.circleWidth
  var dialCenter: Offset by remember { mutableStateOf(Offset.Zero) }
  var lastAzimuth: Float by remember { mutableStateOf(0f) }

  // Threshold for change in azimuth to trigger rotation update
  val azimuthThreshold = 1.0f // Adjust as needed

  Canvas(modifier = modifier) {
    dialCenter = center

    drawContext.canvas.nativeCanvas.apply {
      drawCircle(
        dialCenter.x, dialCenter.y, radius.toPx(), Paint().apply {
          color = dialColor.toArgb()
        }
      )
      drawCircle(dialCenter.x, dialCenter.y, innerRadius.toPx(), Paint().apply {
        color = Color.WHITE
      })
    }

    // Check if the change in azimuth exceeds the threshold
    if (abs(azimuth - lastAzimuth) >= azimuthThreshold) {
      // Rotate and draw the texts based on azimuth
      rotate(-azimuth, pivot = dialCenter) {
        // Draw the changing text part
        for (i in 0..360 step 45) {
          val angleInRadians: Float = i * (PI / 180f).toFloat()
          val textAngle = angleInRadians - (90f * (PI / 180f)).toFloat()
          val textPositionRadius =
            innerRadius.toPx() - style.majorTextSize.toPx() - 8.dp.toPx()
          val x = cos(textAngle) * textPositionRadius + dialCenter.x
          val y = sin(textAngle) * textPositionRadius + dialCenter.y

          drawContext.canvas.nativeCanvas.apply {
            drawText(i.toString(), x, y, Paint().apply {
              textSize = style.majorTextSize.toPx()
              textAlign = Paint.Align.CENTER
            })
          }
        }
      }
      lastAzimuth = azimuth
    } else {
      // If azimuth change is below the threshold, reuse the previous drawing for the changing text part
      rotate(-lastAzimuth, dialCenter) {
        for (i in 0..360 step 45) {
          val angleInRadians: Float = i * (PI / 180f).toFloat()
          val textAngle = angleInRadians - (90f * (PI / 180f)).toFloat()
          val textPositionRadius =
            innerRadius.toPx() - style.majorTextSize.toPx() - 8.dp.toPx()
          val x = cos(textAngle) * textPositionRadius + dialCenter.x
          val y = sin(textAngle) * textPositionRadius + dialCenter.y

          drawContext.canvas.nativeCanvas.apply {
            drawText(i.toString(), x, y, Paint().apply {
              textSize = style.majorTextSize.toPx()
              textAlign = Paint.Align.CENTER
            })
          }
        }
      }
    }
  }
}



