package com.example.learnexus.ui.kelas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.learnexus.ui.components.BottomNavigationBar
import com.example.learnexus.ui.theme.PoppinsFontFamily

@Composable
fun ClassScreen(navController: NavController) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(47.dp))

            Text(
                "Ini Kelas",
                color = Color.Black,
                fontSize = 20.sp,
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.Normal
            )
        }
    }

}