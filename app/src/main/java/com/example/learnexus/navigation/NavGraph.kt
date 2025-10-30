package com.example.learnexus.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.learnexus.ui.home.HomeScreen
import com.example.learnexus.ui.forgotpassword.ForgotPasswordScreen
import com.example.learnexus.ui.forgotpassword.ForgotPasswordSuccessScreen
import com.example.learnexus.ui.login.LoginScreen
import com.example.learnexus.ui.register.RegisterScreen
import com.example.learnexus.ui.register.RegisterSuccessScreen
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            LoginScreen(navController)
        }

        composable("register") {
            RegisterScreen(navController)
        }
        composable("akun_berhasil") {
            RegisterSuccessScreen(navController)
        }
        composable("home") {
            HomeScreen(navController)
        }

        composable("lupa sandi") {
            ForgotPasswordScreen(navController)
        }

        composable("lupa_sandi_berhasil") {
            ForgotPasswordSuccessScreen(navController)
        }


    }
}
