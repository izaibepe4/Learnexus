package com.example.learnexus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.learnexus.navigation.NavGraph // Pastikan import ini ada
// import com.example.learnexus.ui.theme.LearnexusTheme // (Uncomment jika pakai theme)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Inisialisasi NavController
            val navController = rememberNavController()

            // Panggil NavGraph (Bukan AppNavigation lagi)
            NavGraph(navController = navController)
        }
    }
}