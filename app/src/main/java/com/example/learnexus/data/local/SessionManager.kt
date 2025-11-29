package com.example.learnexus.data.local

import android.content.Context
import android.content.SharedPreferences
import com.example.learnexus.data.model.UserData
import com.google.gson.Gson

object SessionManager {
    private const val PREF_NAME = "learnexus_pref"
    private const val KEY_USER = "user_data"

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    // Simpan User (Login)
    fun saveUser(context: Context, user: UserData) {
        val editor = getPreferences(context).edit()
        val json = Gson().toJson(user) // Ubah object User jadi JSON string
        editor.putString(KEY_USER, json)
        editor.apply()
    }

    // Ambil User (Cek Login)
    fun getUser(context: Context): UserData? {
        val json = getPreferences(context).getString(KEY_USER, null)
        return if (json != null) {
            Gson().fromJson(json, UserData::class.java)
        } else {
            null
        }
    }

    // Logout
    fun clearUser(context: Context) {
        val editor = getPreferences(context).edit()
        editor.remove(KEY_USER)
        editor.apply()
    }

    // Helper property untuk akses cepat (opsional, tapi butuh context sekarang)
    // Kita hapus variable currentUser global yang lama agar tidak membingungkan
}