package com.example.faq

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun StartScreen(cropViewModel: CropViewModel,
                onCameraClicked: () -> Unit,){
    val scrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val cropUiState by cropViewModel.uiState.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        CameraCard(cropViewModel = cropViewModel,onCameraClicked=onCameraClicked)
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