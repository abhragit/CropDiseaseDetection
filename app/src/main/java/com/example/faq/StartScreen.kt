package com.example.faq

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartScreen(
    cropViewModel: CropViewModel,
    onCameraClicked: () -> Unit,
    onSupportClicked: () -> Unit,
    onSummerClicked: () -> Unit,
    onWinterClicked: () -> Unit,
    onSchemesClicked: () -> Unit,
    navController: NavController
) {
    val cropUiState by cropViewModel.uiState.collectAsState()
    val authState = cropViewModel.authState.observeAsState()

    val items = ImageList.loadimages()
    val coroutineScope = rememberCoroutineScope() //to execute animation first
    val scrollState = rememberLazyListState()

    LaunchedEffect(authState.value) {
        if (authState.value is AuthState.Unauthenticated) {
            navController.navigate("login")
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("AgriGuard") },
                actions = {
                    IconButton(onClick = { cropViewModel.logout() }) {
                        Icon(Icons.Default.Logout, contentDescription = "Logout")
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LazyRow(
                    state = scrollState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp) // Ensure height is set
                ) {

                    items(items) { item ->
                        CardItem(item)
                    }
                }

                // Spacer for some gap
                Spacer(modifier = Modifier.height(15.dp))
                CameraCard(cropViewModel = cropViewModel, onCameraClicked = onCameraClicked)
                Spacer(modifier = Modifier.height(15.dp))
                Row(modifier = Modifier.fillMaxWidth().height(30.dp)){Text(text="Other Access",fontWeight = FontWeight.Bold,fontStyle = FontStyle.Italic)}
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                  //  item{Row(modifier = Modifier.fillMaxWidth()){Text(text="Other Access",fontWeight = FontWeight.Bold,fontStyle = FontStyle.Italic)} }
                    item{Summer(cropViewModel = cropViewModel, onSummerClicked = onSummerClicked)}
                    item { Winter(cropViewModel = cropViewModel, onWinterClicked = onWinterClicked) }
                    item {GovtSchemes(cropViewModel = cropViewModel, onSchemesClicked = onSchemesClicked)}
                    item{Support(cropViewModel = cropViewModel, onSupportClicked = onSupportClicked)}
                }

            }

        }
        LaunchedEffect(Unit) {
            coroutineScope.launch {
                while (true) {
                    for (i in items.indices) {
                        scrollState.animateScrollToItem(i)
                        delay(2000)
                    }
                }
            }
        }
    }
}
@Composable
fun CameraCard(
    cropViewModel: CropViewModel,
    onCameraClicked: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .background(
                    Brush.linearGradient(
                        colors = listOf(Color(0xFFFF9800), Color(0xFFFFEB3B)), // orange to yellow
                        start = Offset(0f, 0f),
                        end = Offset(1000f, 0f)
                    )
                )
                .fillMaxWidth()
                .border(
                    width = 5.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(16.dp)
                )
                .clickable {
                    cropViewModel.updateClickText("Click Registered")
                    onCameraClicked()
                }
                .padding(horizontal = 16.dp, vertical = 20.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.align(Alignment.Center)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.camera_iconn), // adjust name to match your image
                    contentDescription = "Camera Icon",
                    modifier = Modifier
                        .size(40.dp)
                        .padding(end = 8.dp)
                )
                Text(
                    text = "Click Your Crop Image",
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}



@Composable
fun CardItem(item: Items) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Image(
            painter = painterResource(id = item.imageResourceId),
            contentDescription = "",
            contentScale = ContentScale.Crop, // Ensures image fills without blank space
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f) // Or you can use .height(200.dp) if you want fixed height
                .border(5.dp, color = Color.Black)
        )
    }
}


@Composable
fun  Support(cropViewModel: CropViewModel,onSupportClicked: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(210.dp)
            .clickable {
                cropViewModel.updateClickText("This is clicked")
                onSupportClicked()
            },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFFF9E317), Color(0xFFFBFBFB))
                    ),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(12.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.chatgpt_image_apr_23__2025__02_13_52_am), // Make sure to add this image in res/drawable
                    contentDescription = "Rice Crop Icon",
                    modifier = Modifier.size(100.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Help and Support",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }

}
@Composable
fun  Summer(cropViewModel: CropViewModel,onSummerClicked: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(210.dp)
            .clickable {
                cropViewModel.updateClickText("This is clicked")
                onSummerClicked()
            },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFFE7103C), Color(0xFFFBFBFB))
                    ),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(12.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.winter_crop), // Make sure to add this image in res/drawable
                    contentDescription = "Rice Crop Icon",
                    modifier = Modifier.size(100.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Organic Farming",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }

}
@Composable
fun Winter(cropViewModel: CropViewModel, onWinterClicked: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(210.dp)
            .clickable {
                cropViewModel.updateClickText("This is clicked")
                onWinterClicked()
            },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFF0FB1F5), Color(0xFFFBFBFB))
                    ),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(12.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.weather_icon), // Make sure to add this image in res/drawable
                    contentDescription = "Rice Crop Icon",
                    modifier = Modifier.size(100.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Weather",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun  GovtSchemes(cropViewModel: CropViewModel,onSchemesClicked: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(210.dp)
            .clickable {
                cropViewModel.updateClickText("This is clicked")
                onSchemesClicked()
            },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFFFF9610), Color(0xFFFBFBFB))
                    ),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(12.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.chatgpt_image_apr_23__2025__02_04_40_am), // Make sure to add this image in res/drawable
                    contentDescription = "Rice Crop Icon",
                    modifier = Modifier.size(100.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Government Schemes",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }

}