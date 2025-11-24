package com.example.learnexus.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import com.example.learnexus.R
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.learnexus.data.model.BottomNavItem
import com.example.learnexus.ui.theme.PoppinsFontFamily

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavItem("Beranda", "home", R.drawable.ic_unhome, R.drawable.ic_home),
        BottomNavItem("Kelas", "kelas", R.drawable.ic_unkelas, R.drawable.ic_kelas),
        BottomNavItem("Leaderboard", "leaderboard", R.drawable.ic_unleaderboard, R.drawable.ic_leaderboard),
        BottomNavItem("Profil", "profile", R.drawable.ic_unprofil, R.drawable.ic_profile)
    )

    val navBackStackEntry = navController.currentBackStackEntryAsState().value
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        containerColor = Color.White
    ) {
        items.forEach { item ->
            val isSelected = currentRoute == item.route
            NavigationBarItem(
                icon = {
                    Image(
                        painter = painterResource(id = if (isSelected) item.selectedIconRes else item.unselectedIconRes),
                        contentDescription = item.label,
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = { Text(item.label, fontFamily = PoppinsFontFamily, color = if (isSelected) Color(0xFF27361F) else Color(0xFF888888)) },
                selected = isSelected,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            launchSingleTop = true
                            restoreState = true
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                        }
                    }
                }
            )
        }
    }
}