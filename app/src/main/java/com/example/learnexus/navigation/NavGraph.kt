package com.example.learnexus.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.learnexus.ui.home.HomeScreen
import com.example.learnexus.ui.kelas.ArticleScreen
import com.example.learnexus.ui.kelas.ClassScreen
import com.example.learnexus.ui.kelas.DetailCourseScreen
import com.example.learnexus.ui.kelas.QuizScreen
import com.example.learnexus.ui.kelas.VideoPlayerScreen
import com.example.learnexus.ui.login.LoginScreen
import com.example.learnexus.ui.notification.NotificationScreen
import com.example.learnexus.ui.profil.AboutScreen
import com.example.learnexus.ui.profil.EditProfileScreen
import com.example.learnexus.ui.profil.HelpCenterScreen
import com.example.learnexus.ui.profil.ProfileScreen
import com.example.learnexus.ui.profil.SettingsScreen
import com.example.learnexus.ui.register.RegisterScreen
import com.example.learnexus.ui.register.RegisterSuccessScreen
import com.example.learnexus.ui.forgotpassword.ForgotPasswordScreen
import com.example.learnexus.ui.forgotpassword.ForgotPasswordSuccessScreen
import com.example.learnexus.ui.sosial.SocialScreen

@Composable
fun NavGraph(navController: NavHostController) {
    // Kita tidak butuh ProfileViewModel lagi di sini

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        // --- AUTHENTICATION ---
        composable("login") {
            LoginScreen(navController)
        }

        composable("register") {
            RegisterScreen(navController)
        }

        composable("akun_berhasil") {
            RegisterSuccessScreen(navController)
        }

        composable("lupa sandi") {
            val returnRoute = navController.previousBackStackEntry?.destination?.route
            ForgotPasswordScreen(navController, returnRoute)
        }

        composable("lupa_sandi_berhasil") {
            ForgotPasswordSuccessScreen(navController)
        }

        // --- MAIN FEATURES ---
        composable(
            route = "home?userName={userName}",
            arguments = listOf(
                navArgument("userName") {
                    type = NavType.StringType
                    defaultValue = "User"
                }
            )
        ) { backStackEntry ->
            val userName = backStackEntry.arguments?.getString("userName") ?: "User"
            HomeScreen(navController, userName)
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

        // --- PROFILE & SETTINGS ---
        composable("profil") {
            ProfileScreen(navController = navController)
        }

        composable("settings") {
            SettingsScreen(navController)
        }

        composable("edit_profile") {
            // Tidak perlu kirim viewModel lagi, EditProfileScreen sudah mandiri
            EditProfileScreen(navController)
        }

        composable("about") {
            AboutScreen(navController)
        }

        composable("help_center") {
            HelpCenterScreen(navController)
        }

        // --- COURSE CONTENT ---
        composable(
            route = "detail_course/{courseId}",
            arguments = listOf(navArgument("courseId") { type = NavType.StringType })
        ) { backStackEntry ->
            val courseId = backStackEntry.arguments?.getString("courseId")
            DetailCourseScreen(navController, courseId)
        }

        composable(
            route = "article_screen/{moduleId}",
            arguments = listOf(navArgument("moduleId") { type = NavType.StringType })
        ) { backStackEntry ->
            val moduleId = backStackEntry.arguments?.getString("moduleId")
            ArticleScreen(navController = navController, moduleId = moduleId)
        }

        composable(
            route = "video_screen/{moduleId}",
            arguments = listOf(navArgument("moduleId") { type = NavType.StringType })
        ) { backStackEntry ->
            val moduleId = backStackEntry.arguments?.getString("moduleId")
            VideoPlayerScreen(navController = navController, moduleId = moduleId)
        }

        composable(
            route = "quiz_screen/{moduleId}",
            arguments = listOf(navArgument("moduleId") { type = NavType.StringType })
        ) { backStackEntry ->
            val moduleId = backStackEntry.arguments?.getString("moduleId")
            QuizScreen(navController = navController, moduleId = moduleId)
        }
    }
}