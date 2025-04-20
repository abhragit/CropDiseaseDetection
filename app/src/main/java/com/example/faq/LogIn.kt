package com.example.faq

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@Composable
fun LoginScreen(
cropViewModel: CropViewModel,
    onLoginClick: (String, String) -> Unit,
    onNavigateToSignUp: () -> Unit,
    navController: NavHostController  // Add NavHostController as a parameter
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Login", fontSize = 28.sp, fontWeight = FontWeight.Bold)

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                errorMessage = null
            },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                errorMessage = null
            },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
            visualTransformation = PasswordVisualTransformation()
        )

        if (!errorMessage.isNullOrEmpty()) {
            Text(
                text = errorMessage!!,
                color = androidx.compose.ui.graphics.Color.Red,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Button(
            onClick = {
                if (email.isBlank() || password.isBlank()) {
                    errorMessage = "Please enter both email and password"
                } else {
                    onLoginClick(email, password)
                    // Navigate to StartScreen after successful login
                    navController.navigate(CropAppScreen.Start.name)
                }
            },
            modifier = Modifier.fillMaxWidth().padding(top = 24.dp)
        ) {
            Text("Login")
        }

        TextButton(onClick = onNavigateToSignUp) {
            Text("Don't have an account? Sign up")
        }
    }
}
