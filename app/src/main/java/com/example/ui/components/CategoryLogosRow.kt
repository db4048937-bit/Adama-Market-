package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.GoldPrimary

data class CategoryLogoItem(
    val title: String,
    val subtitle: String,
    val icon: ImageVector,
    val bgGradient: List<Color>,
    val accentColor: Color
)

@Composable
fun CategoryLogosRow(
    selectedCategory: String,
    onSelectCategory: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val items = listOf(
        CategoryLogoItem(
            title = "Phone",
            subtitle = "Mobiles",
            icon = Icons.Default.PhoneAndroid,
            bgGradient = listOf(Color(0xFF0F3460), Color(0xFF16213E)),
            accentColor = Color(0xFF00ADB5)
        ),
        CategoryLogoItem(
            title = "Car",
            subtitle = "Vehicles",
            icon = Icons.Default.DirectionsCar,
            bgGradient = listOf(Color(0xFF800E13), Color(0xFF38040E)),
            accentColor = Color(0xFFFF4D6D)
        ),
        CategoryLogoItem(
            title = "Home",
            subtitle = "Houses",
            icon = Icons.Default.Home,
            bgGradient = listOf(Color(0xFF1B4332), Color(0xFF081C15)),
            accentColor = Color(0xFF52B788)
        ),
        CategoryLogoItem(
            title = "Electric",
            subtitle = "Appliances",
            icon = Icons.Default.FlashOn,
            bgGradient = listOf(Color(0xFF4A3E00), Color(0xFF261F00)),
            accentColor = GoldPrimary
        )
    )

    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Market Categories",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onSurface
            )

            if (selectedCategory != "All Items") {
                Text(
                    text = "View All",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.clickable { onSelectCategory("All Items") }
                )
            }
        }

        Spacer(modifier = Modifier.height(6.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items.forEach { item ->
                val isSelected = selectedCategory.equals(item.title, ignoreCase = true)
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .testTag("category_logo_${item.title.lowercase()}")
                        .clickable {
                            if (isSelected) {
                                onSelectCategory("All Items")
                            } else {
                                onSelectCategory(item.title)
                            }
                        },
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    border = if (isSelected) androidx.compose.foundation.BorderStroke(2.dp, item.accentColor) else null,
                    elevation = CardDefaults.cardElevation(defaultElevation = if (isSelected) 4.dp else 1.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp, horizontal = 4.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Icon Circle
                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .clip(CircleShape)
                                .background(Brush.linearGradient(item.bgGradient))
                                .border(1.dp, item.accentColor.copy(alpha = 0.5f), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.title,
                                tint = item.accentColor,
                                modifier = Modifier.size(24.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = item.title,
                            fontSize = 12.sp,
                            fontWeight = if (isSelected) FontWeight.ExtraBold else FontWeight.Bold,
                            color = if (isSelected) item.accentColor else MaterialTheme.colorScheme.onSurface
                        )

                        Text(
                            text = item.subtitle,
                            fontSize = 9.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}
