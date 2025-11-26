package com.example.learnexus.ui.kelas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.learnexus.data.DummyData
import com.example.learnexus.data.model.ArticleContent
import com.example.learnexus.data.model.ContentType
import com.example.learnexus.ui.theme.PoppinsFontFamily

@Composable
fun ArticleScreen(navController: NavController, moduleId: String?) {
    // Cari modul di seluruh course berdasarkan ID
    val module = DummyData.courses.flatMap { it.modules }.find { it.id.toString() == moduleId }

    if (module == null || module.type != ContentType.ARTICLE) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { Text("Materi tidak ditemukan") }
        return
    }

    val content = module.content as ArticleContent

    Scaffold(
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
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Materi Bacaan",
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = module.title,
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "${module.duration} • Artikel",
                fontFamily = PoppinsFontFamily,
                fontSize = 12.sp,
                color = Color.Gray
            )
            Divider(modifier = Modifier.padding(vertical = 16.dp))

            // Render Body Text
            Text(
                text = content.body.replace("# ", "").replace("**", ""), // Simple cleanup markdown
                fontFamily = PoppinsFontFamily,
                fontSize = 14.sp,
                lineHeight = 24.sp,
                color = Color(0xFF333333)
            )

            Spacer(modifier = Modifier.height(50.dp))

            Button(
                onClick = { navController.popBackStack() },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1F3C2E)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Selesai Membaca", fontFamily = PoppinsFontFamily)
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}