package com.example.learnexus.ui.profil

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.learnexus.R
import com.example.learnexus.data.local.SessionManager
import com.example.learnexus.ui.components.BottomNavigationBar
import com.example.learnexus.ui.theme.PoppinsFontFamily

private val PrimaryDarkGreen = Color(0xFF21431F)
private val PrimaryLightGreen = Color(0xFF3A6C39)
private val SurfaceLight = Color(0xFFF5F5F5)

private data class BadgeItem(val title: String, val date: String)

private val sampleBadges = listOf(
    BadgeItem("Learning", "27 Sept 2025"),
    BadgeItem("Introduction", "27 Sept 2025"),
    BadgeItem("Sustainability", "28 Sept 2025"),
    BadgeItem("Green Workshop", "02 Okt 2025"),
    BadgeItem("Advanced Climate", "12 Okt 2025")
)

@Composable
fun ProfileScreen(
    navController: NavController
    // HAPUS PARAMETER VIEWMODEL DARI SINI
) {
    val context = LocalContext.current

    // Ambil Data User Langsung dari Session Manager
    val user = SessionManager.getUser(context)
    val userName = user?.name ?: "Pengguna"
    val userEmail = user?.email ?: "email@example.com"

    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(SurfaceLight)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 24.dp)
        ) {
            ProfileHeader(
                name = userName,
                email = userEmail,
                // UBAH BARIS INI: Hapus tanda /* dan */
                onSettingsClick = { navController.navigate("settings") }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.verticalGradient(listOf(PrimaryLightGreen, PrimaryDarkGreen)),
                        shape = RoundedCornerShape(15.dp)
                    )
                    .padding(horizontal = 16.dp, vertical = 5.dp)
            ) {
                BadgeSection(title = "Badge", badges = sampleBadges)
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Tombol Logout
            LogoutButton(
                onClick = {
                    // 1. Hapus Sesi
                    SessionManager.clearUser(context)
                    // 2. Kembali ke Login
                    navController.navigate("login") {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
private fun ProfileHeader(
    name: String,
    email: String,
    onSettingsClick: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(listOf(PrimaryLightGreen, PrimaryDarkGreen)),
                    shape = RoundedCornerShape(15.dp)
                )
                .padding(horizontal = 24.dp, vertical = 28.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .size(74.dp)
                        .clip(CircleShape)
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Person, "Avatar", tint = PrimaryDarkGreen, modifier = Modifier.size(36.dp))
                }

                Spacer(modifier = Modifier.height(12.dp))
                Text(name, color = Color.White, fontSize = 20.sp, fontFamily = PoppinsFontFamily, fontWeight = FontWeight.SemiBold)
                Text(email, color = Color.White.copy(alpha = 0.85f), fontSize = 12.sp, fontFamily = PoppinsFontFamily)

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { /* TODO */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    shape = RoundedCornerShape(50),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(Icons.Outlined.Add, null, tint = PrimaryDarkGreen)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Tambahkan Teman", color = PrimaryDarkGreen, fontFamily = PoppinsFontFamily, fontWeight = FontWeight.SemiBold)
                }
            }

            IconButton(
                onClick = onSettingsClick,
                modifier = Modifier.align(Alignment.TopEnd).size(32.dp).background(Color.White.copy(alpha = 0.15f), CircleShape)
            ) {
                Icon(Icons.Filled.Settings, "Settings", tint = Color.White)
            }
        }
    }
}

@Composable
private fun BadgeSection(title: String, badges: List<BadgeItem>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(title, color = Color.White, fontSize = 21.sp, fontFamily = PoppinsFontFamily, fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(badges) { badge ->
                BadgeCardItem(badge.title, badge.date)
            }
        }
    }
}

@Composable
private fun BadgeCardItem(title: String, date: String) {
    Card(
        modifier = Modifier.width(148.dp).height(153.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0x331E1E1E))
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(9.dp, 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Box(
                modifier = Modifier.size(115.dp, 70.dp).background(Color(0x331E1E1E), RoundedCornerShape(10.dp)),
                contentAlignment = Alignment.Center
            ) {
                // Ganti dengan icon dummy jika gambar tidak ada
                Icon(Icons.Default.Person, null, tint = Color.White)
            }
            Text(title, color = Color.White, fontSize = 14.sp, fontFamily = PoppinsFontFamily, fontWeight = FontWeight.SemiBold)
            Text(date, color = Color.White.copy(alpha = 0.8f), fontSize = 11.sp, fontFamily = PoppinsFontFamily)
        }
    }
}

@Composable
private fun LogoutButton(onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(containerColor = PrimaryDarkGreen),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(20.dp, 18.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Outlined.Logout, null, tint = Color.White)
                Spacer(modifier = Modifier.width(12.dp))
                Text("Logout", color = Color.White, fontFamily = PoppinsFontFamily, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
            }
            Icon(Icons.Outlined.ArrowForward, null, tint = Color.White)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileScreenPreview() {
    ProfileScreen(navController = rememberNavController())
}