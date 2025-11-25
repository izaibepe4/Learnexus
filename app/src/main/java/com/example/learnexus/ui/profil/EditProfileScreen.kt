package com.example.learnexus.ui.profil

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.learnexus.ui.theme.PoppinsFontFamily

@Composable
fun EditProfileScreen(
    navController: NavController,
    profileViewModel: ProfileViewModel
) {
    var name by rememberSaveable { mutableStateOf(profileViewModel.uiState.name) }
    val email = profileViewModel.uiState.email

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

            ProfileFieldLabel(text = "Email*", isRequired = true)
            Text(
                text = email,
                color = Color(0xFF9B9B9B),
                fontSize = 16.sp,
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.fillMaxWidth()
            )
            Divider(color = Color(0xFFE0E0E0), thickness = 1.dp)

            Spacer(modifier = Modifier.height(28.dp))

            ProfileFieldLabel(text = "Nama")
            EditableNameField(
                value = name,
                onValueChange = { name = it }
            )
            Divider(color = Color(0xFFE0E0E0), thickness = 1.dp)

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    profileViewModel.updateName(name.trim())
                    navController.popBackStack()
                },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E2C1D)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
            ) {
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
                contentDescription = "Kembali"
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
    EditProfileScreen(
        navController = rememberNavController(),
        profileViewModel = ProfileViewModel()
    )
}

