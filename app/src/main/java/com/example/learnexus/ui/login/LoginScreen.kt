package com.example.learnexus.ui.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.learnexus.R
import com.example.learnexus.data.api.RetrofitClient
import com.example.learnexus.data.local.SessionManager
import com.example.learnexus.data.model.LoginRequest
import com.example.learnexus.ui.theme.PoppinsFontFamily
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(navController: NavController) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope() // Scope untuk API Call

    var email by remember { mutableStateOf("") }
    var isEmailValid by remember { mutableStateOf(true) }
    var password by remember { mutableStateOf("") }
    var isPasswordValid by remember { mutableStateOf(true) }
    var passwordVisible by remember { mutableStateOf(false) }

    // State Loading
    var isLoading by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(131.dp))

        Row {
            Text("Selamat Datang Kembali",
                fontSize = 17.sp,
                color = Color.Black,
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Logo
        Image(
            painter = painterResource(id = R.drawable.logo_black),
            modifier = Modifier.size(243.dp, 72.dp),
            contentDescription = "Logo"
        )

        Spacer(modifier = Modifier.height(66.dp))

        // Email TextField
        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                isEmailValid = it.isNotBlank() && it.contains("@")
            },
            label = {
                Text(
                    "Email",
                    color = Color(0xFF686868),
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.Normal,
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_email),
                    modifier = Modifier.size(20.dp),
                    contentDescription = "Email Icon"
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            shape = RoundedCornerShape(15.dp),
            singleLine = true,
            isError = !isEmailValid
        )

        // Menampilkan warning jika Email tidak valid
        if (!isEmailValid) {
            Text(
                text = "Email tidak valid",
                color = Color.Red,
                fontSize = 12.sp,
                fontFamily = PoppinsFontFamily,
                modifier = Modifier
                    .padding(top = 4.dp)
                    .align(Alignment.Start)
            )
        }

        Spacer(modifier = Modifier.height(13.dp))

        // Password TextField
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                isPasswordValid = it.length >= 8
            },
            label = {
                Text(
                    "Kata Sandi",
                    color = Color(0xFF686868),
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.Normal
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_password),
                    modifier = Modifier.size(18.dp),
                    contentDescription = "Password Icon"
                )
            },
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        painter = painterResource(id = if (passwordVisible) R.drawable.ic_visibility else R.drawable.ic_visibility_off),
                        contentDescription = "Toggle password",
                        modifier = Modifier.size(20.dp)
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            shape = RoundedCornerShape(15.dp),
            singleLine = true,
            isError = !isPasswordValid
        )

        if (!isPasswordValid) {
            Text(
                text = "Minimal 8 karakter",
                color = Color.Red,
                fontSize = 12.sp,
                fontFamily = PoppinsFontFamily,
                modifier = Modifier
                    .padding(top = 4.dp)
                    .align(Alignment.Start)
            )
        }

        Spacer(modifier = Modifier.height(13.dp))

        Text(
            "Lupa Sandi?",
            color = Color.Gray,
            fontSize = 14.sp, fontFamily = PoppinsFontFamily,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable {
                navController.navigate("lupa sandi")
            }
                .align(Alignment.End),
            textDecoration = TextDecoration.Underline
        )

        Spacer(modifier = Modifier.height(34.dp))

        // Login Button dengan API
        val isButtonEnabled = email.isNotBlank() && isEmailValid && isPasswordValid && password.isNotBlank() && !isLoading

        Button(
            onClick = {
                isLoading = true // Mulai Loading
                scope.launch {
                    try {
                        val request = LoginRequest(email, password)
                        // Panggil API Login
                        val response = RetrofitClient.instance.login(request)

                        isLoading = false // Stop Loading

                        if (response.success && response.user != null) {
                            // SIMPAN PERMANEN
                            SessionManager.saveUser(context, response.user)

                            Toast.makeText(context, "Login Berhasil!", Toast.LENGTH_SHORT).show()
                            navController.navigate("home?userName=${response.user.name}") {
                                popUpTo("login") { inclusive = true }
                            }
                        } else {
                            // Pesan Error dari API (misal: "Password salah")
                            Toast.makeText(context, response.message, Toast.LENGTH_LONG).show()
                        }
                    } catch (e: Exception) {
                        isLoading = false
                        e.printStackTrace()
                        // Pesan Error Koneksi
                        val errorMessage = if (e.message?.contains("401") == true) "Email atau Password Salah" else "Gagal terhubung ke server"
                        Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                if (isButtonEnabled) Color.Black else Color.Gray
            ),
            enabled = isButtonEnabled
        ) {
            if (isLoading) {
                CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp), strokeWidth = 2.dp)
            } else {
                Text(
                    "Masuk",
                    color = Color.White,
                    fontSize = 17.sp,
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            HorizontalDivider(
                modifier = Modifier.weight(1f),
                color = Color.Gray
            )
            Text(
                "  Atau dengan  ",
                color = Color.Gray
            )
            HorizontalDivider(
                modifier = Modifier.weight(1f),
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(18.dp))

        Button(
            onClick = { /* Handle Google Sign-In */ },
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Black, RoundedCornerShape(10.dp))
                .height(50.dp),
            shape = RoundedCornerShape(50.dp),
            colors = ButtonDefaults.buttonColors(Color.White)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_google),
                contentDescription = "Google Icon",
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                "Masuk dengan Google",
                color = Color.Black,
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(73.dp))

        Row {
            Text(
                "Belum punya akun? ",
                color = Color.Black,
                fontSize = 14.sp,
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.Normal
            )
            Text(
                "Daftar",
                color = Color.Black,
                fontSize = 14.sp,
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.clickable {
                    navController.navigate("register")
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    val navController = rememberNavController()
    LoginScreen(navController = navController)
}