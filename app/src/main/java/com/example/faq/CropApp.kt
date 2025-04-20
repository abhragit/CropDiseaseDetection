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
    Login(title = "Login")
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
            StartScreen(cropViewModel = cropViewModel,
               onCameraClicked = {
                   cropViewModel.updateSelectedCategory(it.toString())
                    navController.navigate("camera") },
             navController = navController
          )
            //CropApp(navController = navController, cropViewModel = cropViewModel)
        }
        composable("camera") {
            CameraScreen()
        }
    }
}





//            LoginScreen(
//                onLoginClick = { email, password ->
//                    userEmail = email
//                    // Navigate to the StartScreen of the crop app after login
//                    navController.navigate(CropAppScreen.Start.name) {
//                        // Optionally clear the backstack to prevent navigating back to login/signup
//                        popUpTo("Login") { inclusive = true }
//                    }
//                },
//                onNavigateToSignUp = {
//                    navController.navigate("signup") {
//                        popUpTo("login") { inclusive = true }
//                    }
//                },
//
//            )
//
//        }
//
//
//        composable("Signup") {
//            SignUpScreen(
//                onSignUpClick = { email, password ->
//                    userEmail = email
//                    // Navigate to the StartScreen of the crop app after sign up
//                    navController.navigate(CropAppScreen.Start.name) {
//                        // Optionally clear the backstack to prevent navigating back to login/signup
//                        popUpTo("signup") { inclusive = true }
//                    }
//                },
//                onNavigateToLogin = {
//                    navController.popBackStack()
//                }
//          )
//        }

        // Crop App Navigator




//@Composable
//fun CropApp(
//    navController: NavHostController,
//    cropViewModel: CropViewModel
//) {
//    NavHost(navController = navController, startDestination = "start") {
//        composable("start") {
//            StartScreen(
//                cropViewModel = cropViewModel,
//                onCameraClicked = {
//                    cropViewModel.updateSelectedCategory(it.toString())
//                    navController.navigate("camera")
//                },
//                navController = navController
//            )
//        }
//
//        composable("camera") {
//            CameraScreen()
//        }
//    }
//}




//@Composable
//fun CropApp(
//cropViewModel: CropViewModel= viewModel(),
// navController: NavHostController= rememberNavController()
//){
//NavHost( navController = navController, startDestination = CropAppScreen.Start.name){
//composable(route = CropAppScreen.Start.name){
//StartScreen(cropViewModel = cropViewModel, onCameraClicked = {
//    cropViewModel.updateSelectedCategory(it.toString())
//    navController.navigate(CropAppScreen.Camera.name)
//})
//}
//    composable(route = CropAppScreen.Camera.name){
//        CameraScreen()
//    }
//}
//}