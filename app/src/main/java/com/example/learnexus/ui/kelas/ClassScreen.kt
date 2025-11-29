package com.example.learnexus.ui.kelas

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.learnexus.data.api.RetrofitClient
import com.example.learnexus.data.local.SessionManager
import com.example.learnexus.data.model.Course
import com.example.learnexus.ui.components.BottomNavigationBar
import com.example.learnexus.ui.home.getHomeCourseColor
import com.example.learnexus.ui.theme.PoppinsFontFamily
import kotlinx.coroutines.launch
import androidx.compose.ui.platform.LocalContext
@Composable
fun ClassScreen(navController: NavController) {
    // Ambil Context
    val context = LocalContext.current
    // 1. STATE DATA API
    var coursesState by remember { mutableStateOf<List<Course>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }

    // 2. FETCH DATA DARI API (Dengan User ID)
    val scope = rememberCoroutineScope()

    // Fungsi load data agar bisa dipanggil ulang
    fun loadData() {
        scope.launch {
            try {
                // AMBIL USER DARI SESSION MANAGER (YANG BARU)
                val user = SessionManager.getUser(context)
                val userId = user?.id

                // Debug log untuk memastikan ID terbaca
                android.util.Log.d("ClassScreen", "User ID yang dikirim: $userId")

                // Panggil API
                coursesState = RetrofitClient.instance.getAllCourses(userId)
                isLoading = false
            } catch (e: Exception) {
                e.printStackTrace()
                isLoading = false
            }
        }
    }

    LaunchedEffect(Unit) {
        loadData()
    }

    // --- FILTER DATA YANG LEBIH TELITI ---
    // In Progress: Lebih dari 0 TAPI kurang dari 100
    val inProgressCourses = coursesState.filter { it.progressPercent > 0 && it.progressPercent < 100 }

    // Completed: Tepat 100 (atau lebih, untuk jaga-jaga bug backend)
    val completedCourses = coursesState.filter { it.progressPercent >= 100 }

    // Available: Sisa kursus yang progressnya 0
    val availableCourses = coursesState.filter { it.progressPercent == 0 }

    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) },
        containerColor = Color(0xFFFAFAFA)
    ) { innerPadding ->

        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color(0xFF1F291F))
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                contentPadding = PaddingValues(top = 24.dp, bottom = 24.dp)
            ) {

                // --- SECTION 1: LANJUTKAN BELAJAR ---
                if (inProgressCourses.isNotEmpty()) {
                    item {
                        SectionHeader("Lanjutkan Belajar")
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                    item {
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            items(inProgressCourses) { course ->
                                ContinueLearningCardLarge(
                                    course = course,
                                    onClick = { courseId ->
                                        navController.navigate("detail_course/$courseId")
                                    }
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(32.dp))
                    }
                }

                // --- SECTION 2: KURSUS SELESAI ---
                if (completedCourses.isNotEmpty()) {
                    item {
                        SectionHeader("Selesai Dipelajari 🎉")
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                    item {
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            items(completedCourses) { course ->
                                CompletedCourseCard(
                                    course = course,
                                    onClick = { courseId ->
                                        navController.navigate("detail_course/$courseId")
                                    }
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(32.dp))
                    }
                }

                // --- SECTION 3: DAFTAR KELAS TERSEDIA ---
                item {
                    val headerTitle = if (inProgressCourses.isEmpty() && completedCourses.isEmpty())
                        "Daftar Kelas Tersedia"
                    else
                        "Kursus Lainnya"

                    SectionHeader(headerTitle)
                    Spacer(modifier = Modifier.height(16.dp))
                }

                // Tampilkan hanya yang belum diambil (availableCourses)
                // Atau tampilkan semua coursesState jika ingin list lengkap di bawah
                items(availableCourses) { course ->
                    HorizontalCourseCard(
                        course = course,
                        onClick = { courseId ->
                            navController.navigate("detail_course/$courseId")
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

// --- KOMPONEN UI ---

@Composable
fun SectionHeader(title: String) {
    Text(
        text = title,
        fontFamily = PoppinsFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        color = Color(0xFF1F291F)
    )
}

// KARTU LANJUTKAN BELAJAR (DENGAN PROGRESS BAR)
@Composable
fun ContinueLearningCardLarge(
    course: Course,
    onClick: (String) -> Unit
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .width(260.dp)
            .border(1.dp, Color(0xFFEEEEEE), RoundedCornerShape(12.dp))
            .clickable { onClick(course.id) }
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(getHomeCourseColor(course.id)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = course.tags.firstOrNull() ?: "Course",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = course.title,
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))

                // Progress Bar
                Row(verticalAlignment = Alignment.CenterVertically) {
                    val progress = course.progressPercent / 100f
                    LinearProgressIndicator(
                        progress = { progress },
                        modifier = Modifier.weight(1f).height(6.dp).clip(RoundedCornerShape(50)),
                        color = getHomeCourseColor(course.id),
                        trackColor = Color.LightGray.copy(alpha = 0.5f),
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "${course.progressPercent}%",
                        fontSize = 12.sp,
                        fontFamily = PoppinsFontFamily,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}

// KARTU BARU: KURSUS SELESAI
@Composable
fun CompletedCourseCard(
    course: Course,
    onClick: (String) -> Unit
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9)), // Hijau Muda
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = Modifier
            .width(200.dp)
            .border(1.dp, Color(0xFFC8E6C9), RoundedCornerShape(12.dp))
            .clickable { onClick(course.id) }
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF4CAF50)), // Hijau Sukses
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Check, contentDescription = null, tint = Color.White)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = course.title,
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = Color(0xFF1B5E20)
            )
            Text(
                text = "Selesai",
                fontSize = 12.sp,
                color = Color(0xFF4CAF50),
                fontFamily = PoppinsFontFamily
            )
        }
    }
}

// KARTU DAFTAR KELAS (HORIZONTAL)
@Composable
fun HorizontalCourseCard(
    course: Course,
    onClick: (String) -> Unit
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp)
            .border(1.dp, Color(0xFFE0E0E0), RoundedCornerShape(12.dp))
            .clickable { onClick(course.id) }
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .width(100.dp)
                    .fillMaxHeight()
                    .background(getHomeCourseColor(course.id).copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = course.title.take(2).uppercase(),
                    fontWeight = FontWeight.Bold,
                    color = getHomeCourseColor(course.id)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = course.title,
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = course.instructor,
                    fontFamily = PoppinsFontFamily,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ClassScreenPreview() {
    val navController = rememberNavController()
    ClassScreen(navController = navController)
}