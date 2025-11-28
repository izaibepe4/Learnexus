package com.example.learnexus.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.learnexus.R
import com.example.learnexus.data.DummyData
import com.example.learnexus.data.model.Course
import com.example.learnexus.ui.components.BottomNavigationBar
import com.example.learnexus.ui.theme.PoppinsFontFamily

@Composable
fun HomeScreen(navController: NavController) {
    // 1. DATA: Lanjutkan Belajar (Ambil 3 saja sebagai contoh simulasi sedang berjalan)
    val continueCourses = DummyData.courses.take(3)

    // 2. DATA: Rekomendasi Untukmu (Ambil SEMUA 8 Course sesuai request)
    val recommendedCourses = DummyData.courses

    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) },
        containerColor = Color(0xFFF4F4F4)
    ) { innerPadding ->

        // BOX UTAMA
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = innerPadding.calculateBottomPadding())
        ) {

            // LAYER 1: KONTEN SCROLL
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                // Spacer agar konten mulai di bawah Search Bar
                Spacer(modifier = Modifier.height(115.dp))

                HeroCard()

                Column(Modifier.padding(16.dp)) {
                    Spacer(modifier = Modifier.height(16.dp))

                    // --- SECTION 1: Lanjutkan Belajar ---
                    SectionTitle("Lanjutkan Belajar")
                    Spacer(modifier = Modifier.height(16.dp))
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 4.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(continueCourses) { course ->
                            ContinueLearningCard(
                                course = course,
                                onClick = {
                                    navController.navigate("detail_course/${course.id}")
                                }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    // --- SECTION 2: Rekomendasi Untukmu (8 Course) ---
                    SectionTitle("Rekomendasi Untukmu")
                    Spacer(modifier = Modifier.height(16.dp))
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 4.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(recommendedCourses) { course ->
                            RecommendationCard(
                                course = course,
                                onClick = {
                                    navController.navigate("detail_course/${course.id}")
                                }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(80.dp))
                }
            }

            // LAYER 2: HEADER
            HomeHeader(
                query = searchQuery,
                onQueryChange = { searchQuery = it },
                onNotificationClick = { navController.navigate("notification") }
            )
        }
    }
}

// --- KOMPONEN KARTU REKOMENDASI (UPDATED HEIGHT) ---
@Composable
private fun RecommendationCard(
    course: Course,
    onClick: () -> Unit
) {

    val accentColor = getHomeCourseColor(course.id)

    Surface(
        shape = RoundedCornerShape(10.dp),
        tonalElevation = 2.dp,
        color = Color.White,
        modifier = Modifier
            .size(width = 150.dp, height = 165.dp)
            .clickable { onClick() }
    ) {
        Column {
            // Bagian Atas (Icon & Background Warna)
            Box(
                modifier = Modifier

                    .width(150.dp).height(75.dp)
                    .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
                    .background(accentColor.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .clip(CircleShape)
                        .background(accentColor),
                    contentAlignment = Alignment.Center
                ) {
                    // Inisial Judul
                    Text(
                        text = course.title.take(1),
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }
            }

            // Bagian Bawah (Judul & Kategori)
            Column(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
            ) {
                Text(
                    text = course.title,
                    color = Color.Black,
                    fontSize = 12.sp,
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 2,
                    lineHeight = 16.sp,
                    // UPDATE: Tinggi text container ditambah jadi 34.dp
                    modifier = Modifier.height(34.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = course.tags.firstOrNull() ?: "Umum",
                    color = Color.Gray,
                    fontSize = 11.sp,
                    fontFamily = PoppinsFontFamily
                )
            }
        }
    }
}

// --- KOMPONEN LAINNYA ---

@Composable
private fun HomeHeader(
    query: String,
    onQueryChange: (String) -> Unit,
    onNotificationClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxWidth().height(140.dp)) {
        Surface(
            shadowElevation = 6.dp,
            modifier = Modifier.fillMaxWidth().height(115.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f).padding(top = 20.dp)) {
                    Text("Halo, User 👋", color = Color.Black, fontSize = 25.sp, fontFamily = PoppinsFontFamily, fontWeight = FontWeight.Bold)
                }
                IconButton(onClick = onNotificationClick, modifier = Modifier.padding(top = 20.dp)) {
                    Icon(Icons.Outlined.Notifications, "Notifikasi", tint = Color.Black, modifier = Modifier.size(28.dp))
                }
            }
        }
        Box(modifier = Modifier.align(Alignment.BottomCenter)) {
            FloatingSearchBar(query, onQueryChange)
        }
    }
}

@Composable
private fun HeroCard() {
    Image(
        painter = painterResource(id = R.drawable.register_header),
        contentDescription = null,
        modifier = Modifier.fillMaxWidth().height(270.dp),
        contentScale = androidx.compose.ui.layout.ContentScale.Crop
    )
}

// UPDATE: ContinueLearningCard disamakan tingginya agar konsisten
@Composable
private fun ContinueLearningCard(course: Course, onClick: () -> Unit) {
    val accentColor = getHomeCourseColor(course.id)
    val randomProgress = 50

    Surface(
        shape = RoundedCornerShape(10.dp),
        tonalElevation = 2.dp,
        color = Color.White,
        // UPDATE: Tinggi diubah jadi 165.dp
        modifier = Modifier.size(width = 150.dp, height = 165.dp).clickable { onClick() }
    ) {
        Column {
            Box(
                // UPDATE: Tinggi header 75.dp
                modifier = Modifier.width(150.dp).height(75.dp)
                    .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
                    .background(accentColor.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier.size(30.dp).clip(CircleShape).background(accentColor),
                    contentAlignment = Alignment.Center
                ) {
                    Text(course.title.take(1), color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
            Column(modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp)) {
                Text(
                    text = course.title,
                    color = Color.Black, fontSize = 12.sp, fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.SemiBold, maxLines = 2, lineHeight = 16.sp,
                    // UPDATE: Tinggi text container 34.dp
                    modifier = Modifier.height(34.dp)
                )
                Spacer(modifier = Modifier.height(28.dp))
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                    val progressFraction = (randomProgress / 100f)
                    Box(modifier = Modifier.weight(1f).height(6.dp).clip(RoundedCornerShape(50)).background(Color.LightGray.copy(alpha = 0.5f))) {
                        Box(modifier = Modifier.fillMaxHeight().fillMaxWidth(progressFraction).clip(RoundedCornerShape(50)).background(accentColor))
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("$randomProgress%", color = Color.Black, fontSize = 10.sp, fontFamily = PoppinsFontFamily, fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }
}

@Composable
private fun FloatingSearchBar(query: String, onQueryChange: (String) -> Unit) {
    Surface(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp),
        shape = RoundedCornerShape(50), shadowElevation = 8.dp, color = Color.White
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Outlined.Search, "Cari", tint = Color(0xFF9BA0A5), modifier = Modifier.size(20.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Box(modifier = Modifier.weight(1f)) {
                if (query.isBlank()) {
                    Text("Cari materi atau kursus", color = Color(0xFF9BA0A5), fontFamily = PoppinsFontFamily, fontSize = 14.sp)
                }
                BasicTextField(
                    value = query, onValueChange = onQueryChange,
                    textStyle = TextStyle(color = Color.Black, fontFamily = PoppinsFontFamily, fontSize = 14.sp),
                    singleLine = true, modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
private fun SectionTitle(text: String) {
    Text(text, color = Color.Black, fontSize = 18.sp, fontFamily = PoppinsFontFamily, fontWeight = FontWeight.SemiBold)
}

fun getHomeCourseColor(id: String): Color {
    return when {
        id.contains("kotlin") -> Color(0xFF3DDC84)
        id.contains("prod") -> Color(0xFF6200EE)
        id.contains("py") -> Color(0xFF3776AB)
        id.contains("speak") -> Color(0xFFFF5722)
        id.contains("ai") -> Color(0xFF00BCD4)
        id.contains("copy") -> Color(0xFFFFC107)
        id.contains("design") -> Color(0xFFE91E63)
        else -> Color(0xFF1C2431)
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(navController = navController)
}