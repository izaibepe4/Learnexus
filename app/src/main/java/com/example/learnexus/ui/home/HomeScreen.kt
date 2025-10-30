package com.example.learnexus.ui.home

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.learnexus.ui.theme.PoppinsFontFamily

@Composable
fun HomeScreen(navController: NavController) {

    Spacer(modifier = Modifier.height(47.dp))

    Text(
        "Ini homescreen!!",
        color = Color.Black,
        fontSize = 14.sp,
        fontFamily = PoppinsFontFamily,
        fontWeight = FontWeight.Normal
    )
}