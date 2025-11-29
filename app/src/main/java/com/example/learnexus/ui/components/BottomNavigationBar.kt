package com.example.learnexus.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.SupervisedUserCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.School
import androidx.compose.material.icons.outlined.SupervisedUserCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.learnexus.ui.theme.PoppinsFontFamily

// Warna Hijau Brand
val BrandGreen = Color(0xFF1F3C2E)

// Data Class Item Navigasi
data class BottomNavItem(
    val label: String,
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

@Composable
fun BottomNavigationBar(navController: NavController) {
    // Daftar Menu Bawah
    val items = listOf(
        BottomNavItem("Beranda", "home", Icons.Filled.Home, Icons.Outlined.Home),
        BottomNavItem("Kelas", "kelas", Icons.Filled.School, Icons.Outlined.School),
        BottomNavItem("Sosial", "sosial", Icons.Filled.SupervisedUserCircle, Icons.Outlined.SupervisedUserCircle),
        BottomNavItem("Profil", "profil", Icons.Filled.Person, Icons.Outlined.Person)
    )

    // Ambil Rute Sekarang
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        containerColor = Color.White
    ) {
        items.forEach { item ->
            // LOGIKA PENTING: Cek apakah route sekarang MENGANDUNG route item
            // Contoh: "home?userName=iza" mengandung "home" -> TRUE (Nyala)
            val isSelected = currentRoute?.startsWith(item.route) == true

            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    // Agar tidak menumpuk halaman yang sama
                    if (currentRoute?.startsWith(item.route) != true) {
                        navController.navigate(item.route) {
                            // Pop sampai halaman awal graph untuk hindari tumpukan back stack
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                label = {
                    Text(
                        text = item.label,
                        fontFamily = PoppinsFontFamily,
                        fontSize = 10.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                        color = if (isSelected) BrandGreen else Color.Gray
                    )
                },
                icon = {
                    Icon(
                        imageVector = if (isSelected) item.selectedIcon else item.unselectedIcon,
                        contentDescription = item.label,
                        tint = if (isSelected) BrandGreen else Color.Gray
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent // Hilangkan lingkaran background default material3
                )
            )
        }
    }
}