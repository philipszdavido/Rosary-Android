package com.chidumennamdi.rosary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chidumennamdi.rosary.screens.PrayersScreen
import com.chidumennamdi.rosary.screens.SinglePrayerScreen
import com.chidumennamdi.rosary.ui.theme.RosaryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RosaryTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun BottomNavigationBar(
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit
) {
    NavigationBar {
        NavigationBarItem(
            selected = selectedTabIndex == 0,
            onClick = { onTabSelected(0) },
            icon = { Icon(Icons.Default.Menu, contentDescription = "Prayers") },
            label = { Text("Prayers") }
        )
        NavigationBarItem(
            selected = selectedTabIndex == 1,
            onClick = { onTabSelected(1) },
            icon = { Icon(Icons.Default.Add, contentDescription = "Add Custom Prayer") },
            label = { Text("Add Custom Prayer") }
        )
        NavigationBarItem(
            selected = selectedTabIndex == 2,
            onClick = { onTabSelected(2) },
            icon = { Icon(Icons.Default.Settings, contentDescription = "Settings") },
            label = { Text("Settings") }
        )
    }
}

@Composable
fun MyApp() {

    val navController = if (LocalInspectionMode.current) {
        // Return a dummy/fake controller in Preview
        NavHostController(LocalContext.current)
    } else {
        rememberNavController()
    }

    NavHost(navController = navController, startDestination = "single-prayer-screen") {
        composable("home") {
            MainView(modifier = Modifier, navController = navController)
        }

        composable("single-prayer-screen") {
            SinglePrayerScreen()
        }
    }

}

@Composable
fun MainView(modifier: Modifier = Modifier, navController: NavHostController? = null) {
    var selectedTabIndex by remember { mutableStateOf(0) }

    Scaffold(
        modifier = modifier,
        bottomBar = {
            BottomNavigationBar(
                selectedTabIndex = selectedTabIndex,
                onTabSelected = { selectedTabIndex = it }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            when (selectedTabIndex) {
                0 -> PrayersScreen(navController = navController)
                1 -> Text("Add Prayer Screen")
                2 -> Text("Settings Screen")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RosaryTheme {
        MainView(modifier = Modifier, navController = null) // Preview-safe
    }
}
