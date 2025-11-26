package com.example.learnexus.ui.kelas

import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.learnexus.data.DummyData
import com.example.learnexus.data.model.ContentType
import com.example.learnexus.data.model.VideoContent
import com.example.learnexus.ui.theme.PoppinsFontFamily

@Composable
fun VideoPlayerScreen(navController: NavController, moduleId: String?) {
    val module = DummyData.courses.flatMap { it.modules }.find { it.id.toString() == moduleId }

    if (module == null || module.type != ContentType.VIDEO) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { Text("Video tidak ditemukan") }
        return
    }

    val content = module.content as VideoContent

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
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
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
                        webChromeClient = WebChromeClient()
                        webViewClient = WebViewClient()
                        loadUrl(content.videoUrl)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f) // Rasio layar lebar
            )

            Spacer(modifier = Modifier.height(24.dp))

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = module.title,
                    color = Color.White,
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = module.description,
                    color = Color.LightGray,
                    fontFamily = PoppinsFontFamily,
                    fontSize = 14.sp
                )
            }
        }
    }
}