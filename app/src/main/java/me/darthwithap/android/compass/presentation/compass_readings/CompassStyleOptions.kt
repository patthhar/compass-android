package me.darthwithap.android.compass.presentation.compass_readings

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.darthwithap.android.compass.ui.theme.darkGrey
import me.darthwithap.android.compass.ui.theme.darkerGrey
import me.darthwithap.android.compass.ui.theme.dialColor
import me.darthwithap.android.compass.ui.theme.lightBlueDegreeColor
import me.darthwithap.android.compass.ui.theme.lightGrassColor

data class CompassStyleOptions(
  val radius: Dp = 150.dp,
  val circleWidth: Dp = 10.dp,
  val shadowRadius: Dp = 5.dp,
  val northTextHighlightColor: Color = darkerGrey,
  val northDialColor: Color = Color.Red,
  val directionTextHighlightColor: Color = darkGrey,
  val directionDialColor: Color = dialColor,
  val degreesColor: Color = lightBlueDegreeColor,
  val centerMarkerColor: Color = lightGrassColor,
  val majorTextSize: TextUnit = 18.sp,
  val minorTextSize: TextUnit = 14.sp
)
