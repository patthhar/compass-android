package me.darthwithap.android.compass.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import me.darthwithap.android.compass.presentation.compass_readings.Compass
import me.darthwithap.android.compass.presentation.compass_readings.CompassReadingsState

@Composable
fun CompassReadingScreen(
  state: CompassReadingsState
) {
  val context = LocalContext.current

  LaunchedEffect(key1 = state.error) {
    state.error?.let {
      Toast.makeText(context, it.errorMsg, Toast.LENGTH_SHORT).show()
    }
  }

  Column(
    modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text(
      text = "Direction: ${state.compassReading?.direction?.direction}",
      style = MaterialTheme.typography.bodyMedium,
      fontWeight = FontWeight.Bold
    )
    Spacer(modifier = Modifier.height(12.dp))
    Text(text = "Degrees: ${state.compassReading?.normalizedDegrees.toString()}")
    Compass(azimuth = state.compassReading?.normalizedDegrees ?: 0f)
  }
}