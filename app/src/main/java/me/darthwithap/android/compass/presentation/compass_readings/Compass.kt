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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import me.darthwithap.android.compass.ui.theme.dialColor
import kotlin.math.PI
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
  var dialCenter by remember {
    mutableStateOf(Offset.Zero)
  }
  Canvas(modifier = modifier.graphicsLayer()) {
    dialCenter = center
    rotate(-azimuth, dialCenter) {
      drawContext.canvas.nativeCanvas.apply {
        drawCircle(
          dialCenter.x, dialCenter.y, radius.toPx(), Paint().apply {
            color = dialColor.toArgb()
            setShadowLayer(style.shadowRadius.toPx(), 0f, 0f, Color.argb(50, 0, 0, 0))
          }
        )
        drawCircle(dialCenter.x, dialCenter.y, innerRadius.toPx(), Paint().apply {
          color = Color.WHITE
        })

        for (i in 0..360) {
          val angleInRadians: Float = i * (PI / 180f).toFloat()
          val textAngle = angleInRadians - (90f * (PI / 180f)).toFloat()
          if ((i % 45 == 0 && i % 360 != 0) || (i == 0)) {
            val textPositionRadius = innerRadius.toPx() - style.majorTextSize.toPx() - 8.dp.toPx()
            val x = cos(textAngle) * textPositionRadius + dialCenter.x
            val y = sin(textAngle) * textPositionRadius + dialCenter.y

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