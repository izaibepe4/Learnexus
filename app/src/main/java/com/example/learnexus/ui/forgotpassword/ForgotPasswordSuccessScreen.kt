package com.example.learnexus.ui.forgotpassword

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.layout.ContentScale
import com.example.learnexus.R
import com.example.learnexus.ui.theme.PoppinsFontFamily

@Composable
fun ForgotPasswordSuccessScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            // Menggunakan resource dari 'learnexus'
            painter = painterResource(id = R.drawable.bg_sukses),
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
            Spacer(modifier = Modifier.height(220.dp))

            Image(
                // Menggunakan resource dari 'learnexus'
                painter = painterResource(id = R.drawable.ic_check),
                contentDescription = "Change Password Success",
                modifier = Modifier
                    .width(122.dp) // Ukuran disamakan dengan kode asli
                    .height(190.dp) // Ukuran disamakan dengan kode asli
            )

            Spacer(modifier = Modifier.height(34.dp))

            Text(
                // Teks diubah sedikit
                text = "Berhasil Mengganti Kata Sandi",
                fontSize = 25.sp,
                fontFamily = PoppinsFontFamily,
                color = Color.White,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier
                    .width(292.dp)
                    .height(76.dp)
            )

            Spacer(modifier = Modifier.height(178.dp))

            Button(
                // Navigasi diubah ke "login"
                onClick = {
                    navController.navigate("login") {
                        popUpTo(navController.graph.findStartDestination().id) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .align(alignment = Alignment.CenterHorizontally),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF151E11))
            ) {
                Text(
                    // Teks tombol diubah
                    text = "Kembali ke Login",
                    color = Color.White,
                    fontSize = 17.sp,
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}


@Preview
@Composable
fun PreviewForgotPasswordSuccessScreen() {
    ForgotPasswordSuccessScreen(navController = rememberNavController())
}