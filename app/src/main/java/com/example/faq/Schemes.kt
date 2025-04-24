package com.example.faq

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
@Composable
fun Schemes(cropViewModel: CropViewModel) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "🇮🇳 Government Schemes for Farmers",
            style = MaterialTheme.typography.headlineMedium.copy(color = Color(0xFF1B5E20)),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(schemeList) { scheme ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(scheme.url))
                            context.startActivity(intent)
                        },
                    elevation = CardDefaults.cardElevation(6.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        // Indian tricolour bar
                        Column(
                            modifier = Modifier
                                .width(8.dp)
                                .fillMaxHeight()
                        ) {
                            Box(modifier = Modifier
                                .weight(1f)
                                .background(Color(0xFFFF9933))) // Saffron
                            Box(modifier = Modifier
                                .weight(1f)
                                .background(Color.White)) // White
                            Box(modifier = Modifier
                                .weight(1f)
                                .background(Color(0xFF138808))) // Green
                        }

                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                        ) {
                            Text(
                                text = scheme.name,
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                            )
                            Text(
                                text = "Tap to visit official site",
                                style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
                            )
                        }
                    }
                }
            }
        }
    }
}



data class Scheme(val name: String, val url: String)

val schemeList = listOf(
    Scheme("PM-Kisan", "https://pmkisan.gov.in/"),
    Scheme("Pradhan Mantri Fasal Bima Yojana", "https://pmfby.gov.in/"),
    Scheme("Soil Health Card Scheme", "https://soilhealth.dac.gov.in/"),
    Scheme("e-NAM (National Agriculture Market)", "https://enam.gov.in/"),
    Scheme("RKVY (Rashtriya Krishi Vikas Yojana)", "https://rkvy.nic.in/"),
    Scheme("Kisan Credit Card (KCC)", "https://vikaspedia.in/agriculture/agri-finance/kisan-credit-card"),
    Scheme("PM Krishi Sinchai Yojana", "https://pmksy.gov.in/"),
    Scheme("National Mission on Sustainable Agriculture", "https://agricoop.gov.in/"),
    Scheme("Paramparagat Krishi Vikas Yojana (Organic Farming)", "https://pgsindia-ncof.gov.in/")
)
