package com.example.learnexus.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.learnexus.ui.home.HomeScreen
import com.example.learnexus.ui.kelas.ClassScreen
import com.example.learnexus.ui.leaderboard.LeaderboardScreen
import com.example.learnexus.ui.forgotpassword.ForgotPasswordScreen
import com.example.learnexus.ui.forgotpassword.ForgotPasswordSuccessScreen
import com.example.learnexus.ui.login.LoginScreen
import com.example.learnexus.ui.profil.AboutScreen
import com.example.learnexus.ui.profil.EditProfileScreen
import com.example.learnexus.ui.profil.HelpCenterScreen
import com.example.learnexus.ui.profil.ProfileScreen
import com.example.learnexus.ui.profil.ProfileViewModel
import com.example.learnexus.ui.profil.SettingsScreen
import com.example.learnexus.ui.register.RegisterScreen
import com.example.learnexus.ui.register.RegisterSuccessScreen
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val profileViewModel: ProfileViewModel = viewModel()

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
        composable("kelas") {
            ClassScreen(navController)
        }

        composable("leaderboard") {
            LeaderboardScreen(navController)
        }

        composable("profile") {
            ProfileScreen(navController, profileViewModel)
        }
        composable("settings") {
            SettingsScreen(navController)
        }
        composable("edit_profile") {
            EditProfileScreen(navController, profileViewModel)
        }
        composable("about") {
            AboutScreen(navController)
        }
        composable("help_center") {
            HelpCenterScreen(navController)
        }
        composable("lupa sandi") {
            val returnRoute = navController.previousBackStackEntry?.destination?.route
            ForgotPasswordScreen(navController, returnRoute)
        }

        composable("lupa_sandi_berhasil") {
            ForgotPasswordSuccessScreen(navController)
        }


    }
}
