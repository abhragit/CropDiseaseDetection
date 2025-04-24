package com.example.faq

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.Brush

@Composable
fun WinterCropScreen(cropViewModel: CropViewModel) {
    val context = LocalContext.current
    var weather by remember { mutableStateOf<WeatherResponse?>(null) }
    var error by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        getLocation(context) { location ->
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.open-meteo.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service = retrofit.create(OpenMeteoApi::class.java)

            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val result = service.getCurrentWeather(
                        latitude = location.latitude,
                        longitude = location.longitude,
                        current = "temperature_2m,wind_speed_10m",
                        hourly = "temperature_2m,relative_humidity_2m,wind_speed_10m"
                    )
                    withContext(Dispatchers.Main) {
                        weather = result
                    }
                } catch (e: Exception) {
                    error = "❌ Failed to fetch weather: ${e.message}"
                }
            }
        }
    }

    val fadeIn = remember { Animatable(0f) }

    LaunchedEffect(weather) {
        fadeIn.animateTo(1f, animationSpec = tween(durationMillis = 1000))
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF90CAF9), Color(0xFFE3F2FD))
                )
            )
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .graphicsLayer(alpha = fadeIn.value)
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("🌦️ Weather Assistant", fontSize = 24.sp, fontWeight = FontWeight.Bold)

            weather?.let {
                Card(
                    elevation = CardDefaults.cardElevation(8.dp),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        val icon = when {
                            it.current.temperature_2m > 30 -> "☀️"
                            it.current.wind_speed_10m > 15 -> "🌬️"
                            it.current.temperature_2m < 10 -> "❄️"
                            else -> "🌤️"
                        }

                        Text("$icon Current Temperature: ${it.current.temperature_2m}°C", fontSize = 18.sp)
                        Text("💨 Wind Speed: ${it.current.wind_speed_10m} km/h", fontSize = 18.sp)
                        Spacer(modifier = Modifier.height(8.dp))

                        val suggestion = when {
                            it.current.wind_speed_10m > 15 -> "🌬️ Avoid spraying today due to strong winds."
                            it.current.temperature_2m < 10 -> "❄️ Avoid sowing now, it's too cold."
                            else -> "✅ Good time for field activity."
                        }

                        Text("📌 Suggestion: $suggestion", fontWeight = FontWeight.Medium)
                    }
                }
            }

            if (error.isNotEmpty()) {
                Text(error, color = Color.Red)
            }
        }
    }
}

interface OpenMeteoApi {
    @GET("forecast")
    suspend fun getCurrentWeather(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("current") current: String,
        @Query("hourly") hourly: String
    ): WeatherResponse
}

data class WeatherResponse(
    val current: CurrentWeather
)

data class CurrentWeather(
    val temperature_2m: Float,
    val wind_speed_10m: Float
)

fun getLocation(context: Context, onLocationFetched: (Location) -> Unit) {
    val fused = LocationServices.getFusedLocationProviderClient(context)
    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
        fused.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                onLocationFetched(location)
            } else {
                Log.e("WeatherApp", "Location is null")
            }
        }
    } else {
        Log.e("WeatherApp", "Location permission not granted")
    }
}

