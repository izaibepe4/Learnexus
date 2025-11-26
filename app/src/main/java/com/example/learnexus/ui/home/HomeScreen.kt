package com.example.learnexus.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.learnexus.R
import com.example.learnexus.ui.components.BottomNavigationBar
import com.example.learnexus.ui.theme.PoppinsFontFamily

private data class CourseCardData(
    val title: String,
    val subtitle: String,
    val progress: Int,
    val accent: Color
)

private data class RecommendationCardData(
    val title: String,
    val subtitle: String,
    val accent: Color
)

@Composable
fun HomeScreen(navController: NavController) {
    val continueCourses = listOf(
        CourseCardData(
            title = "Manajemen Waktu Produktif",
            subtitle = "Skill Development",
            progress = 75,
            accent = Color(0xFF49A362)
        ),
        CourseCardData(
            title = "Memahami Dasar Kotlin",
            subtitle = "Pemrograman",
            progress = 50,
            accent = Color(0xFF1C2431)
        ),
        CourseCardData(
            title = "Memahami Dasar Python",
            subtitle = "Pemrograman",
            progress = 85,
            accent = Color(0xFF1C2431)
        ),
        CourseCardData(
            title = "Dasar-dasar Public Speaking",
            subtitle = "Softskill",
            progress = 25,
            accent = Color(0xFF1C2431)
        ),
        CourseCardData(
            title = "12345678901234567890123456789012345",
            subtitle = "Softskill",
            progress = 25,
            accent = Color(0xFF1C2431)
        )
    )

    val recommendations = listOf(
        RecommendationCardData(
            title = "Apa itu AI (Artificial Intelligence) ?",
            subtitle = "Teknologi",
            accent = Color(0xFF272B40)
        ),
        RecommendationCardData(
            title = "Copywriting untuk Media Sosial",
            subtitle = "Komunikasi",
            accent = Color(0xFFF5BF55)
        ),
        RecommendationCardData(
            title = "1234567890123456789012345678901234567890123",
            subtitle = "Komunikasi",
            accent = Color(0xFFF5BF55)
        )
    )

    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            HomeHeader(
                query = searchQuery,
                onQueryChange = { searchQuery = it }
            )
        },
        bottomBar = { BottomNavigationBar(navController = navController) },
        containerColor = Color(0xFFF4F4F4)
    ) { innerPadding ->
        Column (modifier = Modifier
            .padding(innerPadding)   // balikin padding dari Scaffold
            .fillMaxSize()
            .verticalScroll(rememberScrollState())

        ){
        Column {
            Spacer(modifier = Modifier.height(99.dp))
            HeroCard() }
        Column(
            Modifier.padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            SectionTitle("Lanjutkan Belajar")
            Spacer(modifier = Modifier.height(16.dp))
            LazyRow(
                contentPadding = PaddingValues(horizontal = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(continueCourses) { course ->
                    ContinueLearningCard(course)
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
            SectionTitle("Rekomendasi Untukmu")
            Spacer(modifier = Modifier.height(16.dp))
            LazyRow(
                contentPadding = PaddingValues(horizontal = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(recommendations) { item ->
                    RecommendationCard(item)
                }
            }

        }}
    }
}

@Composable
private fun HomeHeader(
    query: String,
    onQueryChange: (String) -> Unit
) {
    Surface(
        shadowElevation = 6.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(108.dp)
                .padding(top = 35.dp, bottom = 10.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Halo, User 👋",
                        color = Color.Black,
                        fontSize = 25.sp,
                        fontFamily = PoppinsFontFamily,
                        fontWeight = FontWeight.Bold
                    )

                }
                IconButton(onClick = { /* TODO: Notification */ }) {
                    Icon(
                        imageVector = Icons.Outlined.Notifications,
                        contentDescription = "Notifikasi",
                        tint = Color.Black,
                        modifier = Modifier.size(25.dp)
                    )
                }
            }


        }
    }
    Column(){
        Spacer(modifier = Modifier.height(85.dp))
        FloatingSearchBar(
            query = query,
            onQueryChange = onQueryChange
        )}

}

@Composable
private fun HeroCard() {
    Image(
        painter = painterResource(id = R.drawable.register_header),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .height(270.dp)
    )
}




@Composable
private fun ContinueLearningCard(data: CourseCardData) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        tonalElevation = 2.dp,
        color = Color.White,
        modifier = Modifier
            .size(width = 150.dp, height = 140.dp)
    ) {
        Column(

        ) {
            Box(
                modifier = Modifier
                    .width(150.dp).height(80.dp)
                    .clip(
                        RoundedCornerShape(
                            topStart = 10.dp,
                            topEnd = 10.dp,
                            bottomStart = 0.dp,
                            bottomEnd = 0.dp
                        )
                    )
                    .background(data.accent.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(18.dp)
                        .clip(CircleShape)
                        .background(data.accent)
                )
            }
            Column(
                modifier = Modifier.padding(horizontal = 4.dp)
            ) {
                val displayedTitle = if (data.title.length > 30) {
                    data.title.take(30).trimEnd() + "..."
                } else {
                    data.title
                }
                Text(
                    text = displayedTitle,
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 2.dp)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth().padding(bottom = 2.dp)

                ) {
                    val progressFraction = (data.progress / 100f).coerceIn(0f, 1f)
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(22.dp)
                            .clip(RoundedCornerShape(50))
                            .border(
                                width = 1.dp,
                                color = Color.Black,
                                shape = RoundedCornerShape(50)
                            )
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth(progressFraction)
                                .clip(RoundedCornerShape(50))
                                .background(data.accent)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "${data.progress} %",
                        color = Color.Black,
                        fontSize = 14.sp,
                        fontFamily = PoppinsFontFamily,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

@Composable
private fun FloatingSearchBar(
    query: String,
    onQueryChange: (String) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp),
        shape = RoundedCornerShape(50),
        shadowElevation = 8.dp,
        color = Color.White
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.Search,
                contentDescription = "Cari",
                tint = Color(0xFF9BA0A5),
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Box(modifier = Modifier.weight(1f)) {
                if (query.isBlank()) {
                    Text(
                        text = "Cari materi atau kursus",
                        color = Color(0xFF9BA0A5),
                        fontFamily = PoppinsFontFamily,
                        fontSize = 14.sp
                    )
                }
                BasicTextField(
                    value = query,
                    onValueChange = onQueryChange,
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontFamily = PoppinsFontFamily,
                        fontSize = 14.sp
                    ),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
private fun RecommendationCard(data: RecommendationCardData) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        tonalElevation = 2.dp,
        color = Color.White,
        modifier = Modifier
            .size(width = 150.dp, height = 140.dp)
    ) {
        Column(

        ) {
            Box(
                modifier = Modifier
                    .width(150.dp).height(80.dp)
                    .clip(
                        RoundedCornerShape(
                            topStart = 10.dp,
                            topEnd = 10.dp,
                            bottomStart = 0.dp,
                            bottomEnd = 0.dp
                        )
                    )
                    .background(data.accent.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(18.dp)
                        .clip(CircleShape)
                        .background(data.accent)
                )
            }
            Column(
                modifier = Modifier.padding(horizontal = 4.dp)
            ) {
                val displayedTitle = if (data.title.length > 40) {
                    data.title.take(30).trimEnd() + "..."
                } else {
                    data.title
                }
                Text(
                    text = displayedTitle,
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 2.dp)
                )}
        }
    }
}

@Composable
private fun SectionTitle(text: String) {
    Text(
        text = text,
        color = Color.Black,
        fontSize = 18.sp,
        fontFamily = PoppinsFontFamily,
        fontWeight = FontWeight.SemiBold
    )
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(navController = navController)
}