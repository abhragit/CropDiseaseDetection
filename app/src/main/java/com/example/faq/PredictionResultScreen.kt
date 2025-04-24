package com.example.faq


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PredictionResultScreen(
    predictionViewModel: PredictionViewModel
) {
    val prediction by predictionViewModel.predictionResult.collectAsState()
    val isLoading by predictionViewModel.isLoading.collectAsState()
    val error by predictionViewModel.errorMessage.collectAsState()
//    val prediction by viewModel.predictionResult.collectAsState()
//    val isLoading by viewModel.isLoading.collectAsState()
//    val error by viewModel.errorMessage.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            isLoading -> CircularProgressIndicator()
            prediction != null -> {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Disease: ${prediction!!.disease}", fontSize = 20.sp)
                    Text("Confidence: ${prediction!!.confidence}", fontSize = 16.sp)
                   // Text("Message: ${prediction!!.message}", fontSize = 16.sp)
                }
            }
            error != null -> {
                Text("Error: $error", color = Color.Red)
            }
            else -> {
                Text("No prediction yet.")
            }
        }
    }
}
