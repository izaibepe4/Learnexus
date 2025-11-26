package com.example.learnexus.ui.kelas

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.learnexus.data.DummyData
import com.example.learnexus.data.model.ContentType
import com.example.learnexus.data.model.QuizContent
import com.example.learnexus.ui.theme.PoppinsFontFamily

@Composable
fun QuizScreen(navController: NavController, moduleId: String?) {
    val module = DummyData.courses.flatMap { it.modules }.find { it.id.toString() == moduleId }

    if (module == null || module.type != ContentType.QUIZ) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { Text("Kuis Error") }
        return
    }

    val quizData = module.content as QuizContent

    // STATE UNTUK LOGIKA KUIS
    var currentQuestionIndex by remember { mutableStateOf(0) }
    var selectedAnswerIndex by remember { mutableStateOf<Int?>(null) }
    var score by remember { mutableStateOf(0) }
    var isQuizFinished by remember { mutableStateOf(false) }

    val currentQuestion = quizData.questions[currentQuestionIndex]

    Scaffold(
        containerColor = Color.White,
        topBar = {
            if (!isQuizFinished) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 40.dp, start = 16.dp, end = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.Close, contentDescription = "Quit")
                    }
                    Text(
                        text = "${currentQuestionIndex + 1}/${quizData.questions.size}",
                        fontFamily = PoppinsFontFamily,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding).padding(24.dp).fillMaxSize()) {

            if (isQuizFinished) {
                // --- HASIL AKHIR ---
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("🎉", fontSize = 60.sp)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Kuis Selesai!",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = PoppinsFontFamily
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Skor Kamu: $score / ${quizData.questions.size}",
                        fontSize = 18.sp,
                        fontFamily = PoppinsFontFamily,
                        color = if (score > quizData.questions.size / 2) Color(0xFF1F3C2E) else Color.Red
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                    Button(
                        onClick = { navController.popBackStack() },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1F3C2E)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Kembali ke Modul", fontFamily = PoppinsFontFamily)
                    }
                }
            } else {
                // --- TAMPILAN PERTANYAAN ---
                LinearProgressIndicator(
                    progress = (currentQuestionIndex + 1) / quizData.questions.size.toFloat(),
                    modifier = Modifier.fillMaxWidth().height(8.dp).clip(RoundedCornerShape(4.dp)),
                    color = Color(0xFF1F3C2E)
                )

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = currentQuestion.text,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = PoppinsFontFamily,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(24.dp))

                // List Pilihan Jawaban
                currentQuestion.options.forEachIndexed { index, optionText ->
                    val isSelected = selectedAnswerIndex == index
                    val containerColor = if (isSelected) Color(0xFF1F3C2E) else Color.White
                    val contentColor = if (isSelected) Color.White else Color.Black
                    val borderColor = if (isSelected) Color(0xFF1F3C2E) else Color(0xFFE0E0E0)

                    Card(
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = containerColor),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp)
                            .border(1.dp, borderColor, RoundedCornerShape(12.dp))
                            .clickable { selectedAnswerIndex = index }
                    ) {
                        Text(
                            text = optionText,
                            modifier = Modifier.padding(16.dp),
                            fontFamily = PoppinsFontFamily,
                            color = contentColor
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                // Tombol Lanjut
                Button(
                    onClick = {
                        if (selectedAnswerIndex != null) {
                            // Cek Jawaban Benar
                            if (selectedAnswerIndex == currentQuestion.correctAnswerIndex) {
                                score++
                            }
                            // Pindah Soal
                            if (currentQuestionIndex < quizData.questions.size - 1) {
                                currentQuestionIndex++
                                selectedAnswerIndex = null
                            } else {
                                isQuizFinished = true
                            }
                        }
                    },
                    enabled = selectedAnswerIndex != null, // Matikan jika belum pilih
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1F3C2E)),
                    modifier = Modifier.fillMaxWidth().height(50.dp)
                ) {
                    Text(
                        text = if (currentQuestionIndex == quizData.questions.size - 1) "Selesai" else "Lanjut",
                        fontFamily = PoppinsFontFamily,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}