package me.darthwithap.android.compass

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import me.darthwithap.android.compass.presentation.CompassReadingScreen
import me.darthwithap.android.compass.presentation.Routes
import me.darthwithap.android.compass.presentation.compass_readings.CompassReadingsViewModel
import me.darthwithap.android.compass.ui.theme.CompassAndroidTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CompassAndroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel = hiltViewModel<CompassReadingsViewModel>()
                    val state = viewModel.state

                    NavHost(
                        navController = rememberNavController(),
                        startDestination = Routes.CompassReadingScreen
                    ) {
                        composable(route = Routes.CompassReadingScreen) {
                            CompassReadingScreen(state)
                        }
                    }
                }
            }
        }
    }
}