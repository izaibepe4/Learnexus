package com.example.learnexus.data.model

// Data yang dikirim ke Server
data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String
)

// Data respon dari Server
data class AuthResponse(
    val success: Boolean,
    val message: String
)

data class LoginRequest(
    val email: String,
    val password: String
)

data class LoginResponse(
    val success: Boolean,
    val message: String,
    val user: UserData? = null
)

data class UserData(
    val id: Int,
    val name: String,
    val email: String
)

data class ProgressRequest(
    val user_id: Int,
    val course_id: String,
    val module_id: Int
)

data class UpdateProfileRequest(
    val name: String
)