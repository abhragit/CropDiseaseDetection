package com.example.faq

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


enum class CropAppScreen(val title:String){
    Start(title = "Start"),
    Camera(title = "Scrap"),
}

@Composable
fun CropApp(
    cropViewModel: CropViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = CropAppScreen.Start.name) {
composable(route = CropAppScreen.Start.name){
    StartScreen(cropViewModel = cropViewModel,
        onCameraClicked = {
            cropViewModel.updateSelectedCategory(it.toString())
            navController.navigate(CropAppScreen.Camera.name)
        })
}
composable(route=CropAppScreen.Camera.name){
    CameraScreen()
}
    }
}
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