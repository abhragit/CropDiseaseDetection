package com.example.faq

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun StartScreen(cropViewModel: CropViewModel,
                onCameraClicked: () -> Unit,
                navController: NavController){
    val scrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val cropUiState by cropViewModel.uiState.collectAsState()
   val authState = cropViewModel.authState.observeAsState()
    LaunchedEffect (authState.value){
        when(authState.value){
            is AuthState.Unauthenticated -> navController.navigate("login")
            else->Unit
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        CameraCard(cropViewModel = cropViewModel,onCameraClicked=onCameraClicked)
        Spacer(modifier = Modifier.height(16.dp))
        TextButton(onClick = {
            cropViewModel.logout()
        }) {
            Text(text = "Logout")
        }
    }


}
@Composable
fun CameraCard(cropViewModel: CropViewModel ,onCameraClicked:()->Unit){
    Row(
        modifier = Modifier.background(  Color(9, 142, 22, 255))
            .fillMaxWidth()
            .border(5.dp, color = Color.Black)
            .padding(horizontal = 16.dp, vertical = 20.dp).clickable {cropViewModel.updateClickText("This is clicked")
                onCameraClicked()},
        horizontalArrangement = Arrangement.Start,

        ) {
        Text(
            text = "Click Your Crop Image",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontStyle = FontStyle.Italic
        )
    }
}