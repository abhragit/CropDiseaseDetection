package com.example.faq

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.*
import androidx.navigation.NavController

@Composable
fun SignUpScreen(
    navController: NavController,
    cropViewModel: CropViewModel
//    onSignUpClick: (String, String) -> Unit,
//    onNavigateToLogin: () -> Unit,

) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
  val authState= cropViewModel.authState.observeAsState()
    val context = LocalContext.current
    LaunchedEffect(authState.value){
when(authState.value){
    is AuthState.Authenticated -> navController.navigate("start")
    is AuthState.Error ->Toast.makeText(context,(authState.value as AuthState.Error).message,Toast.LENGTH_SHORT).show()
    else->Unit
    }}
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Sign Up", fontSize = 28.sp, fontWeight =FontWeight.Bold)

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
            //visualTransformation = PasswordVisualTransformation()
        )

        Button(
            onClick = { cropViewModel.signup(email, password)
                //onSignUpClick(email, password)
                      },
            modifier = Modifier.fillMaxWidth().padding(top = 24.dp)
        ) {
            Text("Sign Up")
        }

        TextButton(onClick = {
            navController.navigate("login")
        }
        //onNavigateToLogin
        ) {
            Text("Already have an account? Login")
        }
    }
}
