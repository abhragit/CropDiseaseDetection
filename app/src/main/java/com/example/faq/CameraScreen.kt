package com.example.faq

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun CameraScreen(){

        val navController = rememberNavController()
        val predictionViewModel = PredictionViewModel()

        NavHost(navController = navController, startDestination = "camera") {
            composable("camera") { CameraActivity(navController = navController) }
            composable("predict") { PredictionResultScreen(predictionViewModel = predictionViewModel) }
        }


//    val context = LocalContext.current
//
//    LaunchedEffect(Unit) {
//        val intent = Intent(context, CameraActivity::class.java)
//        context.startActivity(intent)
//    }
}