package com.example.learnexus.data.api

import com.example.learnexus.data.model.Course
import com.example.learnexus.data.model.Module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import com.example.learnexus.data.model.RegisterRequest // Import ini
import com.example.learnexus.data.model.AuthResponse // Import ini
import com.example.learnexus.data.model.LoginRequest
import com.example.learnexus.data.model.LoginResponse
import com.example.learnexus.data.model.ProgressRequest
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query
import com.example.learnexus.data.model.UpdateProfileRequest
import retrofit2.http.PUT

// 1. Interface API (Daftar alamat backend)
interface ApiService {
    // Sesuai route di app.js kamu: app.get('/api/courses')
    @GET("api/courses")
    suspend fun getAllCourses(@Query("user_id") userId: Int? = null): List<Course>

    // Sesuai route: app.get('/api/courses/:id')
    @GET("api/courses/{id}")
    suspend fun getCourseDetail(
        @Path("id") id: String,
        @Query("user_id") userId: Int? = null // Tambahkan ini
    ): Course

    // Sesuai route: app.get('/api/modules/:id')
    @GET("api/modules/{id}")
    suspend fun getModuleDetail(@Path("id") id: String): Module
    // Endpoint Register
    @POST("api/register")
    suspend fun register(@Body request: RegisterRequest): AuthResponse
    @POST("api/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @PUT("api/users/{id}")
    suspend fun updateProfile(
        @Path("id") id: Int,
        @Body request: UpdateProfileRequest
    ): LoginResponse

    // Di dalam interface ApiService
    @POST("api/progress")
    suspend fun updateProgress(@Body request: ProgressRequest): AuthResponse // Kita pakai AuthResponse karena strukturnya mirip (success, message)
}

// 2. Object Retrofit (Mesin penghubung)
object RetrofitClient {
    // KHUSUS EMULATOR ANDROID: Gunakan 10.0.2.2, jangan localhost
    private const val BASE_URL = "http://10.0.2.2:3000/"

    val instance: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}