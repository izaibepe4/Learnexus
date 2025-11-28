package com.example.learnexus.ui.profil

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.learnexus.R
import com.example.learnexus.ui.components.BottomNavigationBar
import com.example.learnexus.ui.theme.PoppinsFontFamily

private val PrimaryDarkGreen = Color(0xFF21431F)
private val PrimaryLightGreen = Color(0xFF3A6C39)
private val SurfaceLight = Color(0xFFF5F5F5)
private data class BadgeItem(
    val title: String,
    val date: String
)

private val sampleBadges = listOf(
    BadgeItem("Learning", "27 Sept 2025"),
    BadgeItem("Introduction to bla bla bla ...", "27 Sept 2025"),
    BadgeItem("Sustainability Basics", "28 Sept 2025"),
    BadgeItem("Green Workshop", "02 Okt 2025"),
    BadgeItem("Advanced Climate Series", "12 Okt 2025")
)

@Composable
fun ProfileScreen(
    navController: NavController,
    profileViewModel: ProfileViewModel
) {
    val uiState = profileViewModel.uiState
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
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
                name = uiState.name,
                email = uiState.email,
                onSettingsClick = { navController.navigate("settings") }
            )
            Spacer(modifier = Modifier.height(24.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(PrimaryLightGreen, PrimaryDarkGreen)
                        ),
                        shape = RoundedCornerShape(15.dp)
                    )
                    .padding(horizontal = 16.dp, vertical = 5.dp)
            ) {
                BadgeSection(
                    title = "Badge",
                    badges = sampleBadges
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            LogoutButton()
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
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        listOf(PrimaryLightGreen, PrimaryDarkGreen)
                    ),
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
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Avatar",
                        tint = PrimaryDarkGreen,
                        modifier = Modifier.size(36.dp)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = name,
                    color = Color.White,
                    fontSize = 20.sp,
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = email,
                    color = Color.White.copy(alpha = 0.85f),
                    fontSize = 12.sp,
                    fontFamily = PoppinsFontFamily
                )

                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { /* TODO */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White
                    ),
                    shape = RoundedCornerShape(50),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Add,
                        contentDescription = null,
                        tint = PrimaryDarkGreen
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Tambahkan Teman",
                        color = PrimaryDarkGreen,
                        fontFamily = PoppinsFontFamily,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            IconButton(
                onClick = onSettingsClick,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(32.dp)
                    .background(
                        color = Color.White.copy(alpha = 0.15f),
                        shape = CircleShape
                    )
            ) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "Settings",
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
private fun ProfileStat(label: String, value: Int) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value.toString(),
            color = Color.White,
            fontSize = 20.sp,
            fontFamily = PoppinsFontFamily,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = label,
            color = Color.White.copy(alpha = 0.8f),
            fontSize = 12.sp,
            fontFamily = PoppinsFontFamily
        )
    }
}

@Composable
private fun BadgeSection(
    title: String,
    badges: List<BadgeItem>
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                color = Color.White,
                fontSize = 21.sp,
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.SemiBold,
            )
        }


        BadgesSection(badges)
    }
}

@Composable
private fun BadgesSection(badges: List<BadgeItem>) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(badges) { badge ->
            BadgeCardItem(
                title = badge.title,
                date = badge.date
            )
        }
    }
}

@Composable
private fun BadgeCardItem(
    title: String,
    date: String
) {
    Card(
        modifier = Modifier
            .width(148.dp)
            .height(153.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0x331E1E1E))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 9.dp, vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Box(
                modifier = Modifier
                    .size(width = 115.dp, height = 70.dp)
                    .background(
                        color = Color(0x331E1E1E),
                        shape = RoundedCornerShape(10.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.superstar_badge),
                    contentDescription = "Badge",
                    modifier = Modifier
                        .width(82.dp)
                        .height(52.dp)
                )
            }
            Text(
                text = title,
                color = Color.White,
                fontSize = 14.sp,
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = date,
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 11.sp,
                fontFamily = PoppinsFontFamily
            )
        }
    }
}

@Composable
private fun LogoutButton() {
    Card(
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(containerColor = PrimaryDarkGreen),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 18.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Outlined.Logout,
                    contentDescription = null,
                    tint = Color.White
                )
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
                imageVector = Icons.Outlined.ArrowForward,
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileScreenPreview() {
    ProfileScreen(
        navController = rememberNavController(),
        profileViewModel = ProfileViewModel()
    )
}