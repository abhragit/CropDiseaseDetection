package com.example.faq

import com.example.faq.AuthState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth


enum class CropAppScreen(val title:String){
    Start(title = "Start"),
    Camera(title = "Scrap"),
    SignUp(title = "SignUp"),
    Login(title = "Login"),
    Support(title="Support"),
    Summer(title="Summer")
}
@Composable
fun AppNavigator(cropViewModel: CropViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(
                navController = navController,
                cropViewModel = cropViewModel,
            )
        }
        composable("signup") {
            SignUpScreen(
                navController = navController,
                cropViewModel = cropViewModel,
            )
        }
        composable("start") {
            StartScreen(
                cropViewModel = cropViewModel,
                onCameraClicked = {
                    cropViewModel.updateSelectedCategory(it.toString())
                    navController.navigate("camera")
                },
                onSupportClicked = {
                    cropViewModel.updateSelectedCategory(it.toString())
                    navController.navigate("support")
                },
                onSummerClicked = {
                    cropViewModel.updateSelectedCategory(it.toString())
                    navController.navigate("summer")
                },
                onWinterClicked = {
                    cropViewModel.updateSelectedCategory(it.toString())
                    navController.navigate("winter")
                },
                onSchemesClicked = {
                    cropViewModel.updateSelectedCategory(it.toString())
                    navController.navigate("schemes")
                },
                navController = navController
            )
            //CropApp(navController = navController, cropViewModel = cropViewModel)
        }
        composable("camera") {
            CameraScreen()
        }
        composable("support") {
            SupportScreen(cropViewModel = cropViewModel)
        }
        composable("summer") {
            SummerCropScreen(cropViewModel = cropViewModel)
        }
        composable("winter") {
            WinterCropScreen(cropViewModel = cropViewModel)
        }
        composable("schemes") {
            Schemes(cropViewModel = cropViewModel)
        }
    }
}
