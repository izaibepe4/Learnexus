package com.example.learnexus.ui.profil

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.learnexus.data.api.RetrofitClient
import com.example.learnexus.data.local.SessionManager
import com.example.learnexus.data.model.UpdateProfileRequest
import com.example.learnexus.ui.theme.PoppinsFontFamily
import kotlinx.coroutines.launch

@Composable
fun EditProfileScreen(
    navController: NavController
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    // Ambil data user saat ini dari Session Local
    val currentUser = SessionManager.getUser(context)

    // State untuk Form
    var name by remember { mutableStateOf(currentUser?.name ?: "") }
    val email = currentUser?.email ?: ""

    var isLoading by remember { mutableStateOf(false) }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color.White)
                .padding(horizontal = 20.dp, vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            EditProfileHeader(onBackClick = { navController.popBackStack() })

            Spacer(modifier = Modifier.height(32.dp))
            AvatarSection()
            Spacer(modifier = Modifier.height(32.dp))

            // EMAIL (Read Only)
            ProfileFieldLabel(text = "Email*", isRequired = true)
            Text(
                text = email,
                color = Color(0xFF9B9B9B),
                fontSize = 16.sp,
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.fillMaxWidth()
            )
            HorizontalDivider(color = Color(0xFFE0E0E0), thickness = 1.dp, modifier = Modifier.padding(vertical = 8.dp))

            Spacer(modifier = Modifier.height(20.dp))

            // NAMA (Editable)
            ProfileFieldLabel(text = "Nama")
            EditableNameField(
                value = name,
                onValueChange = { name = it }
            )
            HorizontalDivider(color = Color(0xFFE0E0E0), thickness = 1.dp, modifier = Modifier.padding(vertical = 8.dp))

            Spacer(modifier = Modifier.weight(1f))

            // TOMBOL SIMPAN
            Button(
                onClick = {
                    if (currentUser != null && name.isNotBlank()) {
                        isLoading = true
                        scope.launch {
                            try {
                                // 1. Panggil API Update
                                val request = UpdateProfileRequest(name = name.trim())
                                val response = RetrofitClient.instance.updateProfile(currentUser.id, request)

                                isLoading = false

                                if (response.success && response.user != null) {
                                    // 2. Update Session Lokal
                                    SessionManager.saveUser(context, response.user)

                                    Toast.makeText(context, "Profil berhasil diperbarui!", Toast.LENGTH_SHORT).show()
                                    navController.popBackStack() // Kembali ke halaman Profil
                                } else {
                                    Toast.makeText(context, "Gagal: ${response.message}", Toast.LENGTH_SHORT).show()
                                }
                            } catch (e: Exception) {
                                isLoading = false
                                e.printStackTrace()
                                Toast.makeText(context, "Error: ${e.localizedMessage}", Toast.LENGTH_SHORT).show()
                            }
                        }
                    } else {
                        Toast.makeText(context, "Nama tidak boleh kosong", Toast.LENGTH_SHORT).show()
                    }
                },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E2C1D)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                enabled = !isLoading
            ) {
                if (isLoading) {
                    CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
                } else {
                    Text(
                        text = "Simpan",
                        color = Color.White,
                        fontFamily = PoppinsFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

// --- KOMPONEN UI BAWAH ---

@Composable
private fun EditProfileHeader(onBackClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onBackClick,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color(0xFFEDEDED))
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                contentDescription = "Kembali",
                tint = Color(0xFF1B1B1B)
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = "Edit Profil",
            fontSize = 22.sp,
            fontFamily = PoppinsFontFamily,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF1B1B1B)
        )
    }
}

@Composable
private fun AvatarSection() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(76.dp)
                .clip(CircleShape)
                .background(Color(0xFFEDEDED))
                .border(2.dp, Color(0xFF1E2C1D), CircleShape)
        ) {
            Icon(
                imageVector = Icons.Filled.CameraAlt,
                contentDescription = "Ganti Foto",
                tint = Color(0xFF1E2C1D)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Ganti Foto",
            fontFamily = PoppinsFontFamily,
            fontSize = 14.sp,
            color = Color(0xFF4A4A4A)
        )
    }
}

@Composable
private fun ProfileFieldLabel(text: String, isRequired: Boolean = false) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = text,
            fontSize = 14.sp,
            fontFamily = PoppinsFontFamily,
            fontWeight = FontWeight.Normal,
            color = Color(0xFF5E5E5E)
        )
        if (isRequired) {
            Text(
                text = "*",
                color = Color(0xFFD9534F),
                fontSize = 14.sp,
                modifier = Modifier.padding(start = 2.dp)
            )
        }
    }
}

@Composable
private fun EditableNameField(
    value: String,
    onValueChange: (String) -> Unit
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        textStyle = TextStyle(
            fontFamily = PoppinsFontFamily,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF2A2A2A)
        ),
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 6.dp, bottom = 4.dp),
        decorationBox = { innerTextField ->
            if (value.isEmpty()) {
                Text(
                    text = "Masukkan nama",
                    fontFamily = PoppinsFontFamily,
                    fontSize = 18.sp,
                    color = Color(0xFF9B9B9B),
                    modifier = Modifier.fillMaxWidth()
                )
            }
            innerTextField()
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun EditProfileScreenPreview() {
    EditProfileScreen(navController = rememberNavController())
}