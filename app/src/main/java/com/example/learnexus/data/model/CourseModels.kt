package com.example.learnexus.data.model

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName

// 1. Model Utama Course
data class Course(
    val id: String,
    val title: String,
    val description: String,
    val instructor: String,
    val level: String,
    val tags: List<String> = emptyList(),
    val modules: List<Module> = emptyList(),

    // TAMBAHKAN INI (Default 0 jika user belum ambil course)
    @SerializedName("progress_percent")
    val progressPercent: Int = 0
)

// 2. Model Modul
data class Module(
    val id: Int,
    @SerializedName("course_id")
    val courseId: String = "",
    val title: String,
    val description: String,
    val duration: String,
    val type: ContentType,
    @SerializedName("is_completed")
    val isCompleted: Boolean = false,
    // PENTING: Backend mengirim data mentah (JSON/String) di field "content_data"
    // Kita terima sebagai JsonElement agar fleksibel (bisa String atau Array)
    @SerializedName("content_data")
    private val rawContent: JsonElement? = null
) {
    // Helper: Mengubah data mentah dari Backend menjadi ModuleContent siap pakai di UI
    val content: ModuleContent
        get() {
            return try {
                when (type) {
                    ContentType.VIDEO -> VideoContent(rawContent?.asString ?: "")
                    ContentType.ARTICLE -> ArticleContent(rawContent?.asString ?: "")
                    ContentType.QUIZ -> {
                        // Parse JSON Array dari backend menjadi List<Question>
                        val gson = Gson()
                        val questions = gson.fromJson(rawContent, Array<Question>::class.java)?.toList() ?: emptyList()
                        QuizContent(questions)
                    }
                }
            } catch (e: Exception) {
                // Fallback jika error parsing
                ArticleContent("Gagal memuat konten.")
            }
        }
}

// 3. Enum Tipe Konten
enum class ContentType {
    VIDEO, ARTICLE, QUIZ
}

// 4. Sealed Interface (Tetap sama, untuk Logic UI)
sealed interface ModuleContent

data class VideoContent(val videoUrl: String) : ModuleContent
data class ArticleContent(val body: String) : ModuleContent
data class QuizContent(val questions: List<Question>) : ModuleContent

// 5. Model Pertanyaan Quiz
data class Question(
    val text: String,
    val options: List<String>,
    val correctAnswerIndex: Int
)