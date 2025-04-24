package com.example.faq

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.Agriculture
import androidx.compose.material.icons.filled.Block
import androidx.compose.material.icons.filled.BugReport
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.Fireplace
import androidx.compose.material.icons.filled.Grass
import androidx.compose.material.icons.filled.Landscape
import androidx.compose.material.icons.filled.LocalDining
import androidx.compose.material.icons.filled.Nature
import androidx.compose.material.icons.filled.Opacity
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.filled.Yard
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
@Composable
fun SummerCropScreen(cropViewModel: CropViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE8F5E9))
            .padding(16.dp)
    ) {
        Text(
            text = "🌿 Organic Farming Principles",
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2E7D32)
            ),
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Text(
            text = "Discover how organic farming protects nature and boosts crop health — naturally and sustainably.",
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFF4E342E),
            modifier = Modifier.padding(bottom = 24.dp)
        )

        val principles = listOf(
            Triple("No Synthetic Chemicals", "Avoid chemical fertilizers and pesticides — use compost, manure, and bio-pesticides.", Icons.Default.Block),
            Triple("Natural Weed Control", "Instead of herbicides, use mulching, hand weeding, and crop rotation to manage weeds.", Icons.Default.Nature),
            Triple("Efficient Water Use", "Adopt drip irrigation and mulching to conserve water and maintain soil moisture.", Icons.Default.Opacity),
            Triple("Soil Health Focus", "Boost soil fertility with green manure, compost, and rotating crops.", Icons.Default.Landscape),
            Triple("Pest Control the Natural Way", "Use neem oil, chili-garlic sprays, and companion planting to reduce pests.", Icons.Default.BugReport),
            Triple("Biodiversity Support", "Encourage diversity by planting different crops and preserving native species.", Icons.Default.Eco)
        )

        principles.forEach { (title, description, icon) ->
            OrganicPrincipleCard(title = title, description = description, icon = icon)
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
fun OrganicPrincipleCard(title: String, description: String, icon: ImageVector) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(Color.White)
                .padding(16.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color(0xFF43A047),
                modifier = Modifier.size(36.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2E7D32)
                    )
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF616161)
                )
            }
        }
    }
}
