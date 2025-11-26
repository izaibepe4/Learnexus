package com.example.learnexus.ui.kelas

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.learnexus.data.DummyData
import com.example.learnexus.data.model.Course
import com.example.learnexus.ui.components.BottomNavigationBar
import com.example.learnexus.ui.theme.PoppinsFontFamily

@Composable
fun ClassScreen(navController: NavController) {
    // 1. Ambil Data dari DummyData
    val allCourses = DummyData.courses

    // Simulasi: Anggap user sedang mempelajari 2 kursus pertama
    val inProgressCourses = allCourses.take(2)

    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) },
        containerColor = Color(0xFFFAFAFA)
    ) { innerPadding ->
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
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        SectionHeader("Lanjutkan Belajar")
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Lihat Semua",
                                fontSize = 12.sp,
                                fontFamily = PoppinsFontFamily,
                                color = Color.Gray
                            )
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                contentDescription = null,
                                tint = Color.Gray,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
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
                                    // Navigasi ke Detail Course membawa ID
                                    navController.navigate("detail_course/$courseId")
                                }
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(32.dp))
                }
            }

            // --- SECTION 2: KURSUS LAINNYA ---
            item {
                SectionHeader("Kursus Lainnya")
                Spacer(modifier = Modifier.height(16.dp))
            }

            // List Vertical untuk semua kursus
            items(allCourses) { course ->
                HorizontalCourseCard(
                    course = course,
                    onClick = { courseId ->
                        // Navigasi ke Detail Course membawa ID
                        navController.navigate("detail_course/$courseId")
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
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

@Composable
fun ContinueLearningCardLarge(
    course: Course,
    onClick: (String) -> Unit // Tambahkan parameter onClick
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .width(260.dp)
            .border(1.dp, Color(0xFFEEEEEE), RoundedCornerShape(12.dp))
            .clickable { onClick(course.id) } // Panggil fungsi onClick saat diklik
    ) {
        Column {
            // Placeholder Gambar / Warna
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(getCourseColor(course.id)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = course.tags.firstOrNull() ?: "Course",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }

            // Judul & Deskripsi
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = course.title,
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = course.description,
                    fontFamily = PoppinsFontFamily,
                    fontSize = 12.sp,
                    color = Color.Gray,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
fun HorizontalCourseCard(
    course: Course,
    onClick: (String) -> Unit // Tambahkan parameter onClick
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp)
            .border(1.dp, Color(0xFFE0E0E0), RoundedCornerShape(12.dp))
            .clickable { onClick(course.id) } // Panggil fungsi onClick saat diklik
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            // Kiri: Kotak Gambar/Warna
            Box(
                modifier = Modifier
                    .width(100.dp)
                    .fillMaxHeight()
                    .background(getCourseColor(course.id).copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = course.title.take(2).uppercase(),
                    fontWeight = FontWeight.Bold,
                    color = getCourseColor(course.id)
                )
            }

            // Kanan: Info Text
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

fun getCourseColor(id: String): Color {
    return when {
        id.contains("kotlin") -> Color(0xFF3DDC84)
        id.contains("prod") -> Color(0xFF6200EE)
        id.contains("py") -> Color(0xFF3776AB)
        id.contains("speak") -> Color(0xFFFF5722)
        id.contains("ai") -> Color(0xFF00BCD4)
        id.contains("copy") -> Color(0xFFFFC107)
        id.contains("design") -> Color(0xFFE91E63)
        else -> Color.Gray
    }
}

// --- PREVIEW ---

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ClassScreenPreview() {
    val navController = rememberNavController()
    ClassScreen(navController = navController)
}