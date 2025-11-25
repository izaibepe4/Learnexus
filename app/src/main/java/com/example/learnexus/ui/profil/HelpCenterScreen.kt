package com.example.learnexus.ui.profil

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Phone
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.learnexus.R
import com.example.learnexus.ui.theme.PoppinsFontFamily

@Composable
fun HelpCenterScreen(navController: NavController) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color(0xFFF5F5F5))
                .padding(vertical = 24.dp)
        ) {
            HelpCenterHeader(onBackClick = { navController.popBackStack() })
            Spacer(modifier = Modifier.height(20.dp))
            Image(
                painter = painterResource(id = R.drawable.img_pusat_bantuan),
                contentDescription = "Pusat Bantuan",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(203.dp)
                    .width(393.dp)

            )
            Spacer(modifier = Modifier.height(28.dp))
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .background(Color(0xFFF5F5F5))
                    .padding(horizontal = 20.dp)
            ) {
                Text(
                    text = "Bantuan Pengguna",
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    color = Color(0xFF1B1B1B)
                )

                Spacer(modifier = Modifier.height(16.dp))
                ContactCard(
                    iconBackground = Color(0xFF0F4C1A),
                    icon = Icons.Outlined.Phone,
                    title = "Bantuan Telepon",
                    subtitle = "(021) 6510300"
                )
                Spacer(modifier = Modifier.height(12.dp))
                ContactCard(
                    iconBackground = Color(0xFF154620),
                    icon = Icons.Outlined.Email,
                    title = "Email",
                    subtitle = "help@learnexus.ac.id"
                )
            }
        }
    }
}

@Composable
private fun HelpCenterHeader(onBackClick: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        IconButton(
            onClick = onBackClick,
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .background(Color.White)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                contentDescription = "Kembali",
                tint = Color(0xFF21431F)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = "Pusat Bantuan",
            fontFamily = PoppinsFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
            color = Color(0xFF1B1B1B)
        )
    }
}

@Composable
private fun AssistanceCard() {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.img_pusat_bantuan),
                contentDescription = "Pusat Bantuan",
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Halo ada yang bisa Kami bantu?",
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                color = Color(0xFF1B1B1B),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                textAlign = TextAlign.Start
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
private fun ContactCard(
    iconBackground: Color,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color(0xFFE2E2E2)),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(iconBackground),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = Color.White
                )
            }
            Column {
                Text(
                    text = title,
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = Color(0xFF1B1B1B)
                )
                Text(
                    text = subtitle,
                    fontFamily = PoppinsFontFamily,
                    fontSize = 14.sp,
                    color = Color(0xFF6C6C6C)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HelpCenterPreview() {
    HelpCenterScreen(navController = rememberNavController())
}

