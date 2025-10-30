package com.example.learnexus.ui.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.learnexus.R
import com.example.learnexus.ui.theme.PoppinsFontFamily

@Composable
fun RegisterScreen(navController: NavController) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderSection()

            Spacer(modifier = Modifier.height(47.dp))

            RegisterForm(navController)

            Spacer(modifier = Modifier.height(16.dp))

            // Sign Up Text
            Row {
                Text(
                    "Sudah mempunyai akun? ",
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.Normal
                )
                Text(
                    "Masuk",
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.Bold,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.clickable {
                        navController.navigate("login")
                    }
                )
            }

        }
    }
}

@Composable
fun HeaderSection() {
    Image(
        painter = painterResource(id = R.drawable.register_header),
        contentDescription = "Header",
        modifier = Modifier
            .fillMaxWidth()
            .height(270.dp),
        contentScale = ContentScale.Crop
    )
}
 @Composable
 fun RegisterForm(navController: NavController) {
     var name by remember { mutableStateOf("") }
     var email by remember { mutableStateOf("") }
     var isEmailValid by remember { mutableStateOf(true) }
     var password by remember { mutableStateOf("") }
     var isPasswordValid by remember { mutableStateOf(true) }
     var passwordVisible by remember { mutableStateOf(false) }
     var isChecked by remember { mutableStateOf(false) }

     val annotatedString = buildAnnotatedString {
         append("Dengan melakukan login atau registrasi, Anda menyetujui ")

         withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, fontFamily = PoppinsFontFamily)) {
             append("Syarat & Ketentuan")
         }

         append(" serta ")

         withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, fontFamily = PoppinsFontFamily)) {
             append("Kebijakan Privasi")
         }
     }
     val isFormValid = name.isNotBlank() && email.isNotBlank() && isEmailValid && password.isNotBlank() && isPasswordValid && isChecked

     Column(modifier = Modifier.padding(16.dp)) {
         Text(
             "Daftarkan Diri Anda",
             color = Color.Gray,
             fontSize = 25.sp,
             fontFamily = PoppinsFontFamily,
             fontWeight = FontWeight.Bold,
             textAlign = TextAlign.Center,
             modifier = Modifier.fillMaxWidth()
         )

         Spacer(modifier = Modifier.height(15.dp))

         // nama pic
         OutlinedTextField(
             value = name,
             onValueChange = { name = it },
             label = {
                 Text(
                     "Nama",
                     color = Color(0xFF686868),
                     fontFamily = PoppinsFontFamily,
                     fontWeight = FontWeight.Normal,
                     fontSize = 14.sp,
                 ) },
             leadingIcon = {
                 Icon(
                     painter = painterResource(id = R.drawable.ic_person),
                     modifier = Modifier.size(20.dp),
                     contentDescription = "Name Icon"
                 )
             },
             modifier = Modifier
                 .fillMaxWidth()
                 .height(55.dp),
             shape = RoundedCornerShape(15.dp),
             singleLine = true
         )

         Spacer(modifier = Modifier.height(16.dp))

         // email pic (using same as company email)
         OutlinedTextField(
             value = email,
             onValueChange = {
                 email = it
                 isEmailValid = it.contains("@")
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
         )

         if (!isEmailValid) {
             Text(
                 text = "Email tidak boleh kosong dan harus mengandung @",
                 color = Color.Red,
                 fontSize = 14.sp,
                 fontFamily = PoppinsFontFamily,
                 modifier = Modifier
                     .padding(top = 4.dp)
                     .align(Alignment.Start)
             )
         }

         Spacer(modifier = Modifier.height(16.dp))

         // password pic
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
                         contentDescription = if (passwordVisible) "Hide password" else "Show password",
                         modifier = Modifier.size(20.dp)
                     )
                 }
             },
             modifier = Modifier
                 .fillMaxWidth()
                 .height(60.dp),
             shape = RoundedCornerShape(15.dp),
             singleLine = true
         )

         // Menampilkan warning jika password kurang dari 8 karakter
         if (!isPasswordValid) {
             Text(
                 text = "Minimal 8 karakter",
                 color = Color.Red,
                 fontSize = 14.sp,
                 fontFamily = PoppinsFontFamily,
                 modifier = Modifier
                     .padding(top = 4.dp)
                     .align(Alignment.Start)
             )
         }

         Spacer(modifier = Modifier.height(16.dp))

         Row(verticalAlignment = Alignment.CenterVertically) {
             Checkbox(
                 checked = isChecked,
                 onCheckedChange = { isChecked = it }
             )
             Text(
                 text = annotatedString,
                 fontSize = 12.sp,
                 color = Color.Black,
                 fontFamily = PoppinsFontFamily,
                 fontWeight = FontWeight.Normal
             )
         }

         Spacer(modifier = Modifier.height(16.dp))

         Button(
             onClick = { navController.navigate("home") },
             modifier = Modifier
                 .fillMaxWidth()
                 .height(50.dp),
             shape = RoundedCornerShape(10.dp),
             colors = ButtonDefaults.buttonColors(
                 if (isFormValid) Color(0xFF27361F) else Color(0xFF989898)
             ),
             enabled = isFormValid
         ) {
             Text(
                 text = "Daftar",
                 fontFamily = PoppinsFontFamily,
                 fontWeight = FontWeight.Bold,
                 fontSize = 17.sp,
                 color = Color.White,
             )
         }

         Spacer(modifier = Modifier.height(16.dp))

         Button(
             onClick = { /* Handle Google Sign-Up */ },
             modifier = Modifier
                 .fillMaxWidth()
                 .height(50.dp)
                 .border(1.dp, Color.Black, RoundedCornerShape(10.dp)),
             shape = RoundedCornerShape(10.dp),
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
     }
 }

@Preview
@Composable
fun PreviewRegisterScreen() {
    val navController = rememberNavController()
    RegisterScreen(navController = navController)
}