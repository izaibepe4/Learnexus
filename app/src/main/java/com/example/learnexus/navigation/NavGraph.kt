package com.example.learnexus.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.learnexus.ui.home.HomeScreen
import com.example.learnexus.ui.forgotpassword.ForgotPassword
import com.example.learnexus.ui.login.LoginScreen
import com.example.learnexus.ui.register.RegisterScreen

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

        composable("home") {
            HomeScreen(navController)
        }

        composable("lupa sandi") {
            ForgotPassword(navController)
        }

    }
}
