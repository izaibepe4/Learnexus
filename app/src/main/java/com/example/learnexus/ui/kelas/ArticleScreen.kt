package com.example.learnexus.ui.kelas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
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
import androidx.navigation.NavController
import com.example.learnexus.data.api.RetrofitClient // Import Client API
import com.example.learnexus.data.model.ArticleContent
import com.example.learnexus.data.model.ContentType
import com.example.learnexus.data.model.Module // Import Model Module
import com.example.learnexus.ui.theme.PoppinsFontFamily
import kotlinx.coroutines.launch
import com.example.learnexus.data.local.UserProgressStore
import com.example.learnexus.data.local.SessionManager // Import Session
import com.example.learnexus.data.model.ProgressRequest // Import Model
@Composable
fun ArticleScreen(navController: NavController, moduleId: String?) {
    val context = LocalContext.current
    // 1. STATE DATA
    var module by remember { mutableStateOf<Module?>(null) }
    var isLoading by remember { mutableStateOf(true) }

    // 2. FETCH DATA DARI API
    val scope = rememberCoroutineScope()

    LaunchedEffect(moduleId) {
        if (moduleId != null) {
            scope.launch {
                try {
                    // Panggil API untuk detail modul
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
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = Color(0xFF1F3C2E))
        }
    } else if (module == null || module?.type != ContentType.ARTICLE) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Artikel tidak ditemukan atau gagal dimuat")
        }
    } else {
        // Data Berhasil Dimuat
        val currentModule = module!!
        // Casting konten ke ArticleContent (aman karena sudah dicek tipenya)
        val content = currentModule.content as ArticleContent

        Scaffold(
            containerColor = Color.White,
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
                            .background(Color(0xFF1F3C2E))
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "Materi Bacaan",
                        fontFamily = PoppinsFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                        color = Color.Black
                    )
                }
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 24.dp)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                // Judul Materi
                Text(
                    text = currentModule.title,
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(8.dp))

                // Metadata
                Text(
                    text = "${currentModule.duration} • Artikel",
                    fontFamily = PoppinsFontFamily,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
                Divider(modifier = Modifier.padding(vertical = 16.dp))

                // Isi Artikel
                // replace() sederhana untuk membersihkan simbol markdown dasar
                Text(
                    text = content.body
                        .replace("# ", "")
                        .replace("**", ""),
                    fontFamily = PoppinsFontFamily,
                    fontSize = 14.sp,
                    lineHeight = 24.sp,
                    color = Color(0xFF333333)
                )

                Spacer(modifier = Modifier.height(50.dp))

                // Tombol Selesai
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
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth().height(50.dp)
                ) {
                    Text(
                        text = "Selesai Membaca",
                        fontFamily = PoppinsFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}