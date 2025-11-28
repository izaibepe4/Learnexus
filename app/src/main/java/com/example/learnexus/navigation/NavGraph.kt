package com.example.learnexus.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.learnexus.ui.home.HomeScreen
import com.example.learnexus.ui.kelas.ClassScreen
import com.example.learnexus.ui.sosial.SocialScreen
import com.example.learnexus.ui.forgotpassword.ForgotPasswordScreen
import com.example.learnexus.ui.forgotpassword.ForgotPasswordSuccessScreen
import com.example.learnexus.ui.kelas.ArticleScreen
import com.example.learnexus.ui.kelas.DetailCourseScreen
import com.example.learnexus.ui.kelas.QuizScreen
import com.example.learnexus.ui.kelas.VideoPlayerScreen
import com.example.learnexus.ui.login.LoginScreen
import com.example.learnexus.ui.notification.NotificationScreen
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

        composable("sosial") {
            SocialScreen(navController)
        }

        composable("notification") {
            NotificationScreen(
                userId = "demo-user",
                onBackClick = { navController.popBackStack() }
            )
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

        composable(
            route = "detail_course/{courseId}", // Menangkap ID
            arguments = listOf(navArgument("courseId") { type = NavType.StringType })
        ) { backStackEntry ->
            val courseId = backStackEntry.arguments?.getString("courseId")
            DetailCourseScreen(navController, courseId)
        }
        // --- RUTE ARTIKEL ---
        composable(
            route = "article_screen/{moduleId}",
            arguments = listOf(navArgument("moduleId") { type = NavType.StringType })
        ) { backStackEntry ->
            val moduleId = backStackEntry.arguments?.getString("moduleId")
            ArticleScreen(navController = navController, moduleId = moduleId) // <--- PANGGIL FILE INI
        }

    // --- RUTE VIDEO ---
        composable(
            route = "video_screen/{moduleId}",
            arguments = listOf(navArgument("moduleId") { type = NavType.StringType })
        ) { backStackEntry ->
            val moduleId = backStackEntry.arguments?.getString("moduleId")
            VideoPlayerScreen(navController = navController, moduleId = moduleId) // <--- PANGGIL FILE INI
        }

    // --- RUTE KUIS ---
        composable(
            route = "quiz_screen/{moduleId}",
            arguments = listOf(navArgument("moduleId") { type = NavType.StringType })
        ) { backStackEntry ->
            val moduleId = backStackEntry.arguments?.getString("moduleId")
            QuizScreen(navController = navController, moduleId = moduleId) // <--- PANGGIL FILE INI
        }
    }
}
