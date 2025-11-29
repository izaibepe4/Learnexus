package com.example.learnexus.data.local

import androidx.compose.runtime.mutableStateListOf

// Object ini menyimpan progress user selama aplikasi berjalan
object UserProgressStore {
    // List ID modul yang sedang dikerjakan (Hijau Polos)
    val inProgressModuleIds = mutableStateListOf<Int>()

    // List ID modul yang sudah selesai (Hijau Centang)
    val completedModuleIds = mutableStateListOf<Int>()

    // Fungsi: Tandai sedang dikerjakan (Saat diklik)
    fun markAsInProgress(moduleId: Int) {
        if (!inProgressModuleIds.contains(moduleId) && !completedModuleIds.contains(moduleId)) {
            inProgressModuleIds.add(moduleId)
        }
    }

    // Fungsi: Tandai selesai (Saat tombol 'Selesai' di Artikel/Video diklik)
    fun markAsCompleted(moduleId: Int) {
        if (!completedModuleIds.contains(moduleId)) {
            completedModuleIds.add(moduleId)
            inProgressModuleIds.remove(moduleId) // Hapus dari in-progress
        }
    }

    // Fungsi: Hitung Persentase Progress untuk Home Screen
    fun getCourseProgress(courseModuleIds: List<Int>): Int {
        if (courseModuleIds.isEmpty()) return 0
        val completedCount = courseModuleIds.count { completedModuleIds.contains(it) }
        return (completedCount.toFloat() / courseModuleIds.size.toFloat() * 100).toInt()
    }
}