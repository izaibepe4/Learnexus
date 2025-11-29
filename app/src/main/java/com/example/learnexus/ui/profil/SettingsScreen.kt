package com.example.learnexus.ui.profil

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.HelpOutline
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.VpnKey
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext // Import ini
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.learnexus.data.local.SessionManager // Import Session
import com.example.learnexus.ui.theme.PoppinsFontFamily

@Composable
fun SettingsScreen(navController: NavController) {
    val context = LocalContext.current // Ambil Context

    val settingsOptions = listOf(
        SettingOption(
            title = "Edit Profil",
            icon = Icons.Outlined.Edit,
            onClick = { navController.navigate("edit_profile") }
        ),
        SettingOption(
            title = "Ganti Kata Sandi",
            icon = Icons.Outlined.VpnKey,
            onClick = { navController.navigate("lupa sandi") }
        ),
        SettingOption(
            title = "Bantuan dan Dukungan",
            icon = Icons.Outlined.HelpOutline,
            onClick = { navController.navigate("help_center") }
        ),
        SettingOption(
            title = "Tentang Aplikasi",
            icon = Icons.Outlined.Info,
            onClick = { navController.navigate("about") }
        )
    )

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color(0xFFF5F5F5))
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 24.dp)
        ) {
            SettingsHeader(onBackClick = { navController.popBackStack() })
            Spacer(modifier = Modifier.height(28.dp))

            settingsOptions.forEach { option ->
                ProfileOption(
                    title = option.title,
                    iconStart = option.icon,
                    iconEnd = Icons.Outlined.ChevronRight,
                    onClick = option.onClick
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // TOMBOL LOGOUT YANG BERFUNGSI
            SettingsLogoutButton(
                onLogoutClick = {
                    // 1. Hapus Data Sesi
                    SessionManager.clearUser(context)

                    // 2. Kembali ke Login & Hapus history
                    navController.navigate("login") {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }
    }
}

private data class SettingOption(
    val title: String,
    val icon: ImageVector,
    val onClick: () -> Unit
)

@Composable
fun ProfileOption(
    title: String,
    iconStart: ImageVector,
    iconEnd: ImageVector,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
            .height(60.dp)
            .border(0.5.dp, Color(0xFFB0B0B0), RoundedCornerShape(10.dp))
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF8F8F8)),
        elevation = CardDefaults.elevatedCardElevation(2.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = iconStart,
                contentDescription = null,
                tint = Color(0xFF1D1D1D),
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.width(13.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF1D1D1D)
                )
                Icon(
                    imageVector = iconEnd,
                    contentDescription = null,
                    tint = Color(0xFF1D1D1D),
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Composable
private fun SettingsHeader(onBackClick: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        IconButton(
            onClick = onBackClick,
            modifier = Modifier
                .size(44.dp)
                .background(Color.White, CircleShape)
                .border(0.7.dp, Color(0xFFE0E0E0), CircleShape)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                contentDescription = "Kembali",
                tint = Color(0xFF21431F)
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = "Pengaturan",
            fontSize = 24.sp,
            fontFamily = PoppinsFontFamily,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF1D1D1D)
        )
    }
}

@Composable
private fun SettingsLogoutButton(onLogoutClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
            .clickable { onLogoutClick() }, // Tambahkan clickable di sini
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E2C1D))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 18.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(34.dp)
                        .background(Color(0xFF152414), RoundedCornerShape(10.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Logout,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "Logout",
                    color = Color.White,
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
            }
            Icon(
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SettingsScreenPreview() {
    SettingsScreen(navController = rememberNavController())
}