package com.example.learnexus.ui.kelas

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.PlayCircle
import androidx.compose.material.icons.outlined.Quiz
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.learnexus.data.DummyData
import com.example.learnexus.data.model.ContentType
import com.example.learnexus.data.model.Course
import com.example.learnexus.data.model.Module
import com.example.learnexus.ui.theme.PoppinsFontFamily

// Warna Hijau Tua Khas Desain
val DarkGreen = Color(0xFF1F3C2E)

@Composable
fun DetailCourseScreen(
    navController: NavController,
    courseId: String?
) {
    val course = DummyData.courses.find { it.id == courseId }

    if (course == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Kursus tidak ditemukan")
        }
        return
    }

    Scaffold(
        containerColor = Color.White,
        topBar = {
            // FIX: Tombol Back
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp, start = 24.dp, bottom = 10.dp), // Padding disesuaikan agar tidak ketutup status bar
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        // Logika Back: Cek apakah bisa back, lalu back.
                        if (navController.previousBackStackEntry != null) {
                            navController.popBackStack()
                        }
                    },
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(DarkGreen)
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
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
        ) {
            // --- HEADER CARD ---
            item {
                Spacer(modifier = Modifier.height(8.dp))
                CourseHeaderCard(course)
                Spacer(modifier = Modifier.height(32.dp))
            }

            // --- TIMELINE MODULES ---
            itemsIndexed(course.modules) { index, module ->
                val status = when (index) {
                    0 -> ModuleStatus.COMPLETED
                    1 -> ModuleStatus.ACTIVE
                    else -> ModuleStatus.LOCKED
                }

                val isLastItem = index == course.modules.lastIndex

                ModuleTimelineItem(
                    module = module,
                    status = status,
                    isLastItem = isLastItem,
                    onNavigate = {
                        // FIX: Navigasi sesuai Tipe
                        when (module.type) {
                            ContentType.ARTICLE -> navController.navigate("article_screen/${module.id}")
                            ContentType.QUIZ -> navController.navigate("quiz_screen/${module.id}")
                            ContentType.VIDEO -> navController.navigate("video_screen/${module.id}") // Atau buka player
                        }
                    }
                )
            }

            item { Spacer(modifier = Modifier.height(50.dp)) }
        }
    }
}

// --- KOMPONEN UI ---

@Composable
fun CourseHeaderCard(course: Course) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color(0xFFEEEEEE), RoundedCornerShape(12.dp))
    ) {
        Column {
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
                    fontSize = 28.sp
                )
            }
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = course.title,
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = course.description,
                    fontFamily = PoppinsFontFamily,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

enum class ModuleStatus { LOCKED, ACTIVE, COMPLETED }

@Composable
fun ModuleTimelineItem(
    module: Module,
    status: ModuleStatus,
    isLastItem: Boolean,
    onNavigate: () -> Unit
) {
    // Default: item pertama terbuka (expanded) agar user langsung lihat
    var isExpanded by remember { mutableStateOf(status == ModuleStatus.ACTIVE || status == ModuleStatus.COMPLETED) }

    val rotationState by animateFloatAsState(targetValue = if (isExpanded) 180f else 0f, label = "Arrow")

    Row(modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Min)) {
        // --- TIMELINE KIRI ---
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(40.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
                    .background(if (status == ModuleStatus.LOCKED) Color.White else DarkGreen)
                    .border(2.dp, if (status == ModuleStatus.LOCKED) Color.Gray else DarkGreen, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                if (status == ModuleStatus.COMPLETED) {
                    Icon(Icons.Default.Check, null, tint = Color.White, modifier = Modifier.size(16.dp))
                }
            }
            if (!isLastItem) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(2.dp)
                        .background(if (status == ModuleStatus.COMPLETED) DarkGreen else Color(0xFFE0E0E0))
                )
            }
        }

        // --- KONTEN KANAN ---
        Column(
            modifier = Modifier
                .padding(start = 12.dp, bottom = 32.dp)
                .weight(1f)
        ) {
            // Header: Judul & Icon Type
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { isExpanded = !isExpanded },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = module.title,
                        fontFamily = PoppinsFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = getIconByType(module.type),
                            contentDescription = null,
                            tint = Color.Gray,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = getTypeName(module.type),
                            fontFamily = PoppinsFontFamily,
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    }
                }
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Expand",
                    tint = DarkGreen,
                    modifier = Modifier.size(24.dp).rotate(rotationState)
                )
            }

            // Body: Deskripsi & Tombol/Thumbnail
            AnimatedVisibility(visible = isExpanded) {
                Column(modifier = Modifier.padding(top = 12.dp)) {
                    Text(
                        text = module.description,
                        fontFamily = PoppinsFontFamily,
                        fontSize = 13.sp,
                        color = Color.DarkGray
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Durasi: ${module.duration}",
                        fontSize = 12.sp,
                        color = Color.Gray,
                        fontFamily = PoppinsFontFamily
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    // FIX: Logika Tampilan Video vs Artikel
                    if (module.type == ContentType.VIDEO) {
                        // TAMPILAN KHUSUS VIDEO (Thumbnail Hitam)
                        VideoThumbnailCard(title = module.title, onClick = onNavigate)
                    } else {
                        // TAMPILAN STANDARD (Tombol Hijau)
                        Button(
                            onClick = onNavigate,
                            colors = ButtonDefaults.buttonColors(containerColor = DarkGreen),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = getButtonText(module.type),
                                fontFamily = PoppinsFontFamily,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }
        }
    }
}

// --- KOMPONEN THUMBNAIL VIDEO ---
@Composable
fun VideoThumbnailCard(title: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp) // Tinggi thumbnail
            .clip(RoundedCornerShape(12.dp))
            .background(Color.Black) // Latar hitam
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        // Ikon Play Putih di Tengah
        Icon(
            imageVector = Icons.Filled.PlayArrow,
            contentDescription = "Play",
            tint = Color.White,
            modifier = Modifier.size(48.dp)
        )

        // Judul Video di Pojok Atas (Opsional, sesuai gambar user ada teks)
        Text(
            text = title,
            color = Color.White,
            fontSize = 12.sp,
            fontFamily = PoppinsFontFamily,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(12.dp)
        )
    }
}

// Helper Functions
fun getIconByType(type: ContentType): ImageVector {
    return when (type) {
        ContentType.VIDEO -> Icons.Outlined.PlayCircle
        ContentType.ARTICLE -> Icons.Outlined.Description
        ContentType.QUIZ -> Icons.Outlined.Quiz
    }
}

fun getTypeName(type: ContentType): String {
    return when (type) {
        ContentType.VIDEO -> "Video"
        ContentType.ARTICLE -> "Artikel"
        ContentType.QUIZ -> "Kuis"
    }
}

fun getButtonText(type: ContentType): String {
    return when (type) {
        ContentType.VIDEO -> "Tonton Video" // Fallback text
        ContentType.ARTICLE -> "Lihat Artikel"
        ContentType.QUIZ -> "Mulai Kuis"
    }
}

@Preview(showBackground = true)
@Composable
fun DetailPreview() {
    DetailCourseScreen(rememberNavController(), "kotlin_101")
}