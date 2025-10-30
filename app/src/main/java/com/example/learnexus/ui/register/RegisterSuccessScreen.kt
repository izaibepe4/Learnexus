package com.example.learnexus.ui.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.example.learnexus.R // Import R dari package learnexus
import com.example.learnexus.ui.theme.PoppinsFontFamily // Import Font dari package learnexus

@Composable
fun RegisterSuccessScreen(navController: NavController) { // <-- PERUBAHAN DI SINI
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Background
        Image(
            painter = painterResource(id = R.drawable.bg_sukses), // Pastikan bg_sukses ada di drawable
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(300.dp))

            Image(
                painter = painterResource(id = R.drawable.ic_check), // Pastikan ic_check ada di drawable
                contentDescription = "Berhasil Buat Akun",
                modifier = Modifier.size(117.dp)
            )

            Spacer(modifier = Modifier.height(29.dp))

            Text(
                text = "Berhasil",
                fontSize = 30.sp,
                fontFamily = PoppinsFontFamily,
                color = Color.White,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.ExtraBold
            )

            Text(
                text = "Membuat Akun",
                fontSize = 30.sp,
                fontFamily = PoppinsFontFamily,
                color = Color.White,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.ExtraBold
            )

            Spacer(modifier = Modifier.height(226.dp))

            Button(
                // PERUBAHAN: Navigasi ke "login" dan bersihkan back stack
                onClick = {
                    navController.navigate("login") { // Ganti "login" dengan rute layar Login Anda
                        popUpTo(navController.graph.findStartDestination().id) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF151E11))
            ) {
                Text(
                    text = "Mulai",
                    color = Color.White,
                    fontSize = 17.sp,
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewRegisterSuccessScreen() { // <-- PERUBAHAN DI SINI
    RegisterSuccessScreen(navController = rememberNavController())
}