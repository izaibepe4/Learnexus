package com.example.learnexus.data.model

// 1. Model Utama Course (Mewakili satu mata pelajaran penuh)
data class Course(
    val id: String,
    val title: String,
    val description: String,
    val instructor: String,
    val level: String,       // Contoh: "Pemula", "Menengah"
    val tags: List<String>,  // Contoh: ["Mobile", "Android"]
    val modules: List<Module>
)

// 2. Model Modul (Mewakili satu baris dalam daftar materi)
data class Module(
    val id: Int,
    val title: String,
    val description: String,
    val duration: String,    // Contoh: "10 Menit"
    val type: ContentType,   // Untuk menentukan ikon UI (Video/Artikel/Quiz)
    val content: ModuleContent // Isi detailnya (bisa beda-beda bentuk datanya)
)

// 3. Enum untuk Jenis Konten (Agar terhindar dari Typo string)
enum class ContentType {
    VIDEO,
    ARTICLE,
    QUIZ
}

// 4. Sealed Interface (Kunci agar konten bisa dinamis)
// Ini memungkinkan 'content' memiliki bentuk data yang berbeda-beda
sealed interface ModuleContent

// A. Jika kontennya VIDEO, datanya cuma butuh URL
data class VideoContent(
    val videoUrl: String
) : ModuleContent

// B. Jika kontennya ARTIKEL, datanya butuh Teks Panjang (Body)
data class ArticleContent(
    val body: String
) : ModuleContent

// C. Jika kontennya QUIZ, datanya butuh Daftar Pertanyaan
data class QuizContent(
    val questions: List<Question>
) : ModuleContent

// 5. Model Pertanyaan untuk Quiz
data class Question(
    val text: String,
    val options: List<String>, // Pilihan jawaban A, B, C, D
    val correctAnswerIndex: Int // Kunci jawaban (Indeks 0, 1, 2, atau 3)
)