package com.example.learnexus.ui.kelas

import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.learnexus.data.api.RetrofitClient // Import Client API
import com.example.learnexus.data.local.SessionManager
import com.example.learnexus.data.local.UserProgressStore // Import Store Progress
import com.example.learnexus.data.model.ContentType
import com.example.learnexus.data.model.Module // Import Model Module
import com.example.learnexus.data.model.ProgressRequest
import com.example.learnexus.data.model.VideoContent
import com.example.learnexus.ui.theme.PoppinsFontFamily
import kotlinx.coroutines.launch

@Composable
fun VideoPlayerScreen(navController: NavController, moduleId: String?) {
    val context = LocalContext.current
    // 1. STATE DATA API
    var module by remember { mutableStateOf<Module?>(null) }
    var isLoading by remember { mutableStateOf(true) }

    // 2. FETCH DATA DARI API
    val scope = rememberCoroutineScope()
    LaunchedEffect(moduleId) {
        if (moduleId != null) {
            scope.launch {
                try {
                    // Panggil API Backend
                    module = RetrofitClient.instance.getModuleDetail(moduleId)
                    isLoading = false
                } catch (e: Exception) {
                    e.printStackTrace()
                    isLoading = false
                }
            }
        }
    }

    // 3. TAMPILAN BERDASARKAN STATE
    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black), // Latar hitam saat loading agar tidak silau
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = Color.White)
        }
    } else if (module == null || module?.type != ContentType.VIDEO) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black),
            contentAlignment = Alignment.Center
        ) {
            Text("Video tidak ditemukan", color = Color.White)
        }
    } else {
        // Data Berhasil Dimuat
        val currentModule = module!!
        val content = currentModule.content as VideoContent

        // Konversi URL YouTube biasa ke format Embed
        val embedUrl = if (content.videoUrl.contains("watch?v=")) {
            content.videoUrl.replace("watch?v=", "embed/")
        } else {
            content.videoUrl
        }

        Scaffold(
            containerColor = Color.Black, // Mode bioskop
            topBar = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp, start = 16.dp, bottom = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(Color.White.copy(alpha = 0.2f))
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                }
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                // WebView Youtube Player
                AndroidView(
                    factory = { context ->
                        WebView(context).apply {
                            settings.javaScriptEnabled = true
                            settings.domStorageEnabled = true
                            webChromeClient = WebChromeClient()
                            webViewClient = WebViewClient()
                            loadUrl(embedUrl)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(16f / 9f) // Rasio layar lebar
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Judul, Deskripsi, dan Tombol Selesai
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = currentModule.title,
                        color = Color.White,
                        fontFamily = PoppinsFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = currentModule.description,
                        color = Color.LightGray,
                        fontFamily = PoppinsFontFamily,
                        fontSize = 14.sp
                    )

                    Spacer(modifier = Modifier.height(40.dp)) // Jarak sebelum tombol

                    // TOMBOL SELESAI MENONTON
                    // TOMBOL SELESAI MENONTON
                    Button(
                        onClick = {
                            // 2. Ambil User dari SharedPreferences
                            val user = SessionManager.getUser(context)

                            if (user != null) {
                                scope.launch {
                                    try {
                                        val req = ProgressRequest(
                                            user_id = user.id,
                                            course_id = currentModule.courseId,
                                            module_id = currentModule.id
                                        )
                                        RetrofitClient.instance.updateProgress(req)
                                        UserProgressStore.markAsCompleted(currentModule.id)
                                    } catch (e: Exception) {
                                        e.printStackTrace()
                                    }
                                }
                            }
                            navController.popBackStack()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1F3C2E)),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Text(
                            text = "Selesai Menonton",
                            fontFamily = PoppinsFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}