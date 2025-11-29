package com.example.learnexus.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.automirrored.outlined.ArrowForward // Update import Icon
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext // Pastikan import ini ada
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.learnexus.R
import com.example.learnexus.data.api.RetrofitClient
import com.example.learnexus.data.local.SessionManager
import com.example.learnexus.data.model.Course
import com.example.learnexus.ui.components.BottomNavigationBar
import com.example.learnexus.ui.theme.PoppinsFontFamily
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    navController: NavController,
    userName: String = "User"
) {
    // 1. AMBIL CONTEXT DI SINI (JANGAN DI DALAM LAUNCH)
    val context = LocalContext.current

    // 2. STATE DATA
    var coursesState by remember { mutableStateOf<List<Course>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val scope = rememberCoroutineScope()

    // Fungsi fetch data
    fun loadData() {
        scope.launch {
            try {
                // Ambil ID User Login menggunakan variable 'context' yang sudah diambil di atas
                val userId = SessionManager.getUser(context)?.id

                // Kirim ID User ke API
                val coursesFromApi = RetrofitClient.instance.getAllCourses(userId)

                coursesState = coursesFromApi
                isLoading = false
            } catch (e: Exception) {
                e.printStackTrace()
                errorMessage = "Gagal: ${e.localizedMessage}"
                isLoading = false
            }
        }
    }

    LaunchedEffect(Unit) {
        loadData()
    }

    // FILTER DATA
    val continueCourses = coursesState.filter { it.progressPercent > 0 && it.progressPercent < 100 }
    val recommendedCourses = coursesState

    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) },
        containerColor = Color(0xFFF4F4F4)
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = innerPadding.calculateBottomPadding())
        ) {
            if (isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = Color(0xFF1F3C2E))
                }
            } else if (errorMessage != null) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = errorMessage ?: "Error", color = Color.Red)
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    Spacer(modifier = Modifier.height(115.dp))
                    HeroCard()

                    Column(Modifier.padding(16.dp)) {
                        Spacer(modifier = Modifier.height(16.dp))

                        // --- LOGIKA PROGRESS ---
                        if (continueCourses.isEmpty()) {
                            SectionTitle("Mulai Perjalananmu 🚀")
                            Spacer(modifier = Modifier.height(16.dp))
                            StartLearningCard(onClick = { navController.navigate("kelas") })
                        } else {
                            SectionTitle("Lanjutkan Belajar")
                            Spacer(modifier = Modifier.height(16.dp))
                            LazyRow(
                                contentPadding = PaddingValues(horizontal = 4.dp),
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                items(continueCourses) { course ->
                                    ContinueLearningCard(
                                        course = course,
                                        onClick = { navController.navigate("detail_course/${course.id}") }
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(32.dp))

                        SectionTitle("Rekomendasi Untukmu")
                        Spacer(modifier = Modifier.height(16.dp))
                        LazyRow(
                            contentPadding = PaddingValues(horizontal = 4.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(recommendedCourses) { course ->
                                RecommendationCard(
                                    course = course,
                                    onClick = { navController.navigate("detail_course/${course.id}") }
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(80.dp))
                    }
                }

                HomeHeader(
                    userName = userName,
                    query = searchQuery,
                    onQueryChange = { searchQuery = it },
                    onNotificationClick = {}
                )
            }
        }
    }
}

// --- KOMPONEN UI ---

@Composable
fun StartLearningCard(onClick: () -> Unit) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = Color(0xFF1F3C2E),
        shadowElevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Belum ada kelas aktif",
                    color = Color.White,
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = "Yuk, cari skill baru sekarang!",
                    color = Color.LightGray,
                    fontFamily = PoppinsFontFamily,
                    fontSize = 12.sp
                )
            }
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                // Perbaikan Icon Deprecated
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.ArrowForward,
                    contentDescription = "Mulai",
                    tint = Color(0xFF1F3C2E)
                )
            }
        }
    }
}

@Composable
private fun RecommendationCard(course: Course, onClick: () -> Unit) {
    val accentColor = getHomeCourseColor(course.id)
    Surface(
        shape = RoundedCornerShape(10.dp), tonalElevation = 2.dp, color = Color.White,
        modifier = Modifier.size(width = 150.dp, height = 165.dp).clickable { onClick() }
    ) {
        Column {
            Box(
                modifier = Modifier.width(150.dp).height(75.dp)
                    .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
                    .background(accentColor.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier.size(30.dp).clip(CircleShape).background(accentColor),
                    contentAlignment = Alignment.Center
                ) {
                    Text(course.title.take(1), color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                }
            }
            Column(modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)) {
                Text(
                    text = course.title, color = Color.Black, fontSize = 12.sp, fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.SemiBold, maxLines = 2, lineHeight = 16.sp, modifier = Modifier.height(34.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = course.tags.firstOrNull() ?: "Umum", color = Color.Gray, fontSize = 11.sp, fontFamily = PoppinsFontFamily)
            }
        }
    }
}

@Composable
private fun ContinueLearningCard(course: Course, onClick: () -> Unit) {
    val accentColor = getHomeCourseColor(course.id)
    val progressPercent = course.progressPercent // Ambil dari API

    Surface(
        shape = RoundedCornerShape(10.dp),
        tonalElevation = 2.dp,
        color = Color.White,
        modifier = Modifier
            .size(width = 150.dp, height = 165.dp)
            .clickable { onClick() }
    ) {
        Column {
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
                    Text(
                        text = course.title.take(1),
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Column(modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp)) {
                Text(
                    text = course.title,
                    color = Color.Black,
                    fontSize = 12.sp,
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 2,
                    lineHeight = 16.sp,
                    modifier = Modifier.height(34.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))

                // PROGRESS BAR
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    val progressFraction = (progressPercent / 100f).coerceIn(0f, 1f)

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(6.dp)
                            .clip(RoundedCornerShape(50))
                            .background(Color.LightGray.copy(alpha = 0.5f))
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth(progressFraction)
                                .clip(RoundedCornerShape(50))
                                .background(accentColor)
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "$progressPercent%",
                        color = Color.Black,
                        fontSize = 10.sp,
                        fontFamily = PoppinsFontFamily,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

@Composable
private fun HomeHeader(
    userName: String,
    query: String,
    onQueryChange: (String) -> Unit,
    onNotificationClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxWidth().height(140.dp)) {
        Surface(shadowElevation = 6.dp, modifier = Modifier.fillMaxWidth().height(115.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f).padding(top = 20.dp)) {
                    Text("Halo, $userName 👋", color = Color.Black, fontSize = 25.sp, fontFamily = PoppinsFontFamily, fontWeight = FontWeight.Bold)
                }
                IconButton(onClick = onNotificationClick, modifier = Modifier.padding(top = 20.dp)) {
                    Icon(Icons.Outlined.Notifications, "Notifikasi", tint = Color.Black, modifier = Modifier.size(28.dp))
                }
            }
        }
        Box(modifier = Modifier.align(Alignment.BottomCenter)) { FloatingSearchBar(query, onQueryChange) }
    }
}

@Composable
private fun HeroCard() {
    Image(
        painter = painterResource(id = R.drawable.register_header), contentDescription = null,
        modifier = Modifier.fillMaxWidth().height(270.dp), contentScale = androidx.compose.ui.layout.ContentScale.Crop
    )
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
    HomeScreen(navController = navController, userName = "Umar")
}