package com.chidumennamdi.rosary.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chidumennamdi.rosary.screens.ui.theme.RosaryTheme

@Composable
fun PrayersScreen(navController: NavController?) {
    Scaffold() { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "TUESDAY, JUNE 24",
                style = MaterialTheme.typography.labelSmall,
                color = Color.Gray
            )

            Text(
                text = "Today",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .clickable {
                        println("Hello")
                        navController?.navigate("single-prayer-screen")
                    }
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFF007BFF))
                    .padding(24.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                ) {
                    Text(
                        "Say Rosary",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        "Sorrowful Mysteries",
                        style = MaterialTheme.typography.bodyMedium.copy(color = Color.White)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            FeaturedPrayers()

            Spacer(modifier = Modifier.height(16.dp))

            PrayerList(
                prayers = listOf(
                    "Our Father",
                    "Hail Mary",
                    "Prayer to St. Joseph",
                    "St. Francis De Asisi",
                    "Memorare",
                    "Hail Holy Queen"
                )
            )
        }

    }
}

@Composable
fun PrayerChip(text: String, backgroundColor: Color) {
    Box(
        modifier = Modifier
            //.weight(1f)
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundColor)
            .padding(vertical = 12.dp, horizontal = 8.dp)
    ) {
        Text(
            text = text,
            color = Color.White,
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp
        )
    }
}

@Composable
fun PrayerList(prayers: List<String>) {
    Column {
        prayers.forEach {
            Column(
                modifier = Modifier
                    .clickable {  }
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                ) {
                    Text(text = it, fontSize = 16.sp)
                    Spacer(Modifier.weight(1f))
                    Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = null)
                }
                Divider()
            }
        }
    }
}

@Composable
fun FeaturedPrayers() {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        item {
            PrayerChip("Short Prayer", Color(0xFFFF7D7D))
        }
        item {
            PrayerChip("Dedication (St. Ignatius Loyola)", Color(0xFFFF7D7D))
        }
        item {
            PrayerChip("Morning", Color(0xFFFF9900))
        }
        item {
            PrayerChip("Angelus", Color(0xFFFF9900))
        }
    }
}

@Preview
@Composable
fun PrayersScreenPreview() {
    RosaryTheme {
        PrayersScreen(navController = null)
    }
}
