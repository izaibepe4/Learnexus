package com.example.learnexus.ui.forgotpassword

import androidx.compose.foundation.Image // Pastikan Image di-import
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon // Import Icon tetap ada untuk field password
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.learnexus.ui.theme.PoppinsFontFamily
import com.example.learnexus.R


@Composable
fun ForgotPasswordScreen(navController: NavController) { // <-- PERUBAHAN NAMA FUNGSI
    var email by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    // State terpisah untuk setiap field kata sandi
    var newPasswordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    // Validasi sederhana
    val isPasswordMismatch = newPassword.isNotEmpty() && confirmPassword.isNotEmpty() && newPassword != confirmPassword

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Tombol kembali
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp)
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Image(
                    painter = painterResource(id = R.drawable.btn_back),
                    contentDescription = "Kembali",
                )
            }
        }

        Spacer(modifier = Modifier.height(35.dp))


        Image(
            painter = painterResource(id = R.drawable.img_change_password),
            contentDescription = "Reset Illustration",
            modifier = Modifier
                .fillMaxWidth()
                .height(258.dp)
                .clip(RoundedCornerShape(20.dp))
        )

        Spacer(modifier = Modifier.height(35.dp))

        // Judul
        Text(
            text = "Atur Ulang Kata Sandi",
            fontSize = 30.sp,
            fontFamily = PoppinsFontFamily,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF27361F)
        )

        // Sub-Judul dari UI
        Text(
            text = "Masukkan email dan kata sandi baru untuk masuk ke akun anda",
            fontSize = 14.sp,
            fontFamily = PoppinsFontFamily,
            fontWeight = FontWeight.Normal,
            color = Color(0xFF686868),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 8.dp)
        )

        Spacer(modifier = Modifier.height(46.dp))

        // Input Email (Menggantikan Kata Sandi Lama)
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = {
                Text(
                    "Email",
                    color = Color(0xFF686868),
                    fontSize = 14.sp,
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.Normal
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Email,
                    contentDescription = "Email Icon",
                    modifier = Modifier.size(18.dp)
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            shape = RoundedCornerShape(15.dp),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(13.dp))

        // Input Kata Sandi Baru
        PasswordTextField(
            value = newPassword,
            onValueChange = { newPassword = it },
            label = "Kata Sandi Baru",
            isPasswordVisible = newPasswordVisible,
            onVisibilityToggle = { newPasswordVisible = !newPasswordVisible },
            isError = newPassword.isNotEmpty() && newPassword.length < 8 // Contoh validasi
        )

        Spacer(modifier = Modifier.height(13.dp))

        // Input Konfirmasi Kata Sandi
        PasswordTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = "Konfirmasi Kata Sandi",
            isPasswordVisible = confirmPasswordVisible,
            onVisibilityToggle = { confirmPasswordVisible = !confirmPasswordVisible },
            isError = isPasswordMismatch
        )

        if (isPasswordMismatch) {
            Text(
                text = "Kata sandi tidak cocok",
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, top = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(47.dp))

        // Tombol Ubah Kata Sandi
        val isButtonEnabled = email.isNotEmpty() && newPassword.isNotEmpty() && confirmPassword.isNotEmpty() && !isPasswordMismatch
        Button(
            // <-- PERUBAHAN NAVIGASI
            onClick = { navController.navigate("lupa_sandi_berhasil") },
            enabled = isButtonEnabled,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF27361F),
                disabledContainerColor = Color(0xFF989898)
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = "Ubah Kata Sandi",
                fontSize = 17.sp,
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isPasswordVisible: Boolean,
    onVisibilityToggle: () -> Unit,
    isError: Boolean = false
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(
                label,
                color = Color(0xFF686868),
                fontSize = 14.sp,
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.Normal
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Lock, // Menggunakan ikon Material
                contentDescription = "Password Icon",
                modifier = Modifier.size(18.dp)
            )
        },
        trailingIcon = {
            IconButton(onClick = onVisibilityToggle) {
                Icon(
                    imageVector = if (isPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff, // Menggunakan ikon Material
                    contentDescription = if (isPasswordVisible) "Hide password" else "Show password",
                    modifier = Modifier.size(18.dp)
                )
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        shape = RoundedCornerShape(15.dp),
        singleLine = true,
        isError = isError
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewForgotPasswordScreen() { // <-- PERUBAHAN NAMA PREVIEW
    // Anda mungkin perlu membungkus ini dengan Tema aplikasi Anda
    // LearnNexusTheme {
    ForgotPasswordScreen(navController = rememberNavController()) // <-- PERUBAHAN NAMA FUNGSI
    // }
}