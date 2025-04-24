package com.example.faq

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.ui.platform.LocalContext

@Composable
fun SupportScreen(cropViewModel: CropViewModel) {
        val context = LocalContext.current

        Column(
                modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFFF1F8E9))
                        .padding(16.dp)
        ) {
                Text(
                        text = "Need Help with Your Crops?",
                        style = MaterialTheme.typography.headlineSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF2E7D32)
                        ),
                        modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                        text = "We’re here to assist you in every stage of crop production. Explore the tools and expert support available.",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(bottom = 24.dp)
                )

                SupportCard(
                        title = "Chat with Agri Expert",
                        description = "Get real-time advice on your crop issues from professionals.",
                        icon = Icons.Default.Chat,
                        url = "https://www.kisansupport.com/live-chat",
                        context = context
                )

                Spacer(modifier = Modifier.height(12.dp))

                SupportCard(
                        title = "View Crop Tips",
                        description = "Access guides and seasonal tips for crop health.",
                        icon = Icons.Default.Lightbulb,
                        url = "https://www.krishijagran.com/agriculture-news/crop-care-tips/",
                        context = context
                )

                Spacer(modifier = Modifier.height(12.dp))

                SupportCard(
                        title = "Fertilizer Calculator",
                        description = "Estimate right fertilizer quantity for your crops.",
                        icon = Icons.Default.Calculate,
                        url = "https://fertilizercalculator.in/",
                        context = context
                )
        }
}

@Composable
fun SupportCard(title: String, description: String, icon: ImageVector, url: String, context: Context) {
        Card(
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                                context.startActivity(intent)
                        }
        ) {
                Row(
                        modifier = Modifier
                                .background(Color.White)
                                .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                ) {
                        Icon(
                                imageVector = icon,
                                contentDescription = null,
                                tint = Color(0xFF388E3C),
                                modifier = Modifier.size(40.dp)
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        Column {
                                Text(
                                        text = title,
                                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                                )
                                Text(
                                        text = description,
                                        style = MaterialTheme.typography.bodySmall
                                )
                        }
                }
        }
}
