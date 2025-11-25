package com.example.learnexus.ui.profil

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.learnexus.ui.theme.PoppinsFontFamily

@Composable
fun AboutScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    listOf(Color(0xFF3A6C39), Color(0xFF1F3A1F))
                )
            )
            .padding(horizontal = 20.dp, vertical = 24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AboutHeader(onBackClick = { navController.popBackStack() })
            Spacer(modifier = Modifier.height(36.dp))
            Text(
                text = "Learnexus",
                fontSize = 32.sp,
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(24.dp))
            AboutParagraph(
                "Learnexus adalah Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis vestibulum diam eget magna commodo placerat. Praesent imperdiet vestibulum lorem. Aenean fermentum gravida diam, a tincidunt tellus accumsan vel. Pellentesque scelerisque consectetur aliquam. Curabitur id volutpat ante. Integer ac elit id neque molestie malesuada. Vivamus purus nulla, viverra sed lectus vestibulum, bibendum consectetur felis. Aenean sed diam mollis, auctor felis at, convallis purus."
            )
            Spacer(modifier = Modifier.height(18.dp))
            AboutParagraph(
                "Integer justo est, imperdiet vel mauris nec, ultrices aliquam turpis. Proin ullamcorper pellentesque mi eu bibendum. Morbi sagittis est eu dolor bibendum, ac lobortis leo condimentum. Proin sit amet tempor leo. Sed a efficitur lectus. Quisque eu diam euismod, vehicula ipsum non, cursus urna. Maecenas quis dictum leo."
            )
            Spacer(modifier = Modifier.height(48.dp))
            Text(
                text = buildAnnotatedString {
                    append("Versi Aplikasi: ")
                    withStyle(SpanStyle(fontWeight = FontWeight.SemiBold)) {
                        append("Learnexus 1.0.0")
                    }
                },
                fontSize = 14.sp,
                fontFamily = PoppinsFontFamily,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun AboutHeader(onBackClick: () -> Unit) {
    Spacer(modifier = Modifier.height(10.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onBackClick,
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.2f))
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                contentDescription = "Back",
                tint = Color.White
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = "Tentang Aplikasi",
            fontSize = 24.sp,
            fontFamily = PoppinsFontFamily,
            fontWeight = FontWeight.SemiBold,
            color = Color.White
        )
    }
}

@Composable
private fun AboutParagraph(text: String) {
    Text(
        text = text,
        fontSize = 15.sp,
        fontFamily = PoppinsFontFamily,
        color = Color.White,
        textAlign = TextAlign.Justify
    )
}

@Preview(showBackground = true)
@Composable
private fun AboutScreenPreview() {
    AboutScreen(navController = rememberNavController())
}


