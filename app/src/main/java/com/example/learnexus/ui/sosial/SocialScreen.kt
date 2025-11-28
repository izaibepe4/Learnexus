package com.example.learnexus.ui.sosial

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.learnexus.R
import com.example.learnexus.ui.components.BottomNavigationBar

import com.example.learnexus.ui.theme.PoppinsFontFamily
private val PrimaryDarkGreen = Color(0xFF21431F)
private val PrimaryLightGreen = Color(0xFF3A6C39)
private val SurfaceLight = Color(0xFFF5F5F5)
@Composable
fun SocialScreen(navController: NavController) {
    val requests = remember { mutableStateListOf(*socialRequests().toTypedArray()) }
    var selectedRequest by remember { mutableStateOf<SocialRequest?>(null) }
    var profileRequest by remember { mutableStateOf<SocialRequest?>(null) }
    var showAddFriendDialog by remember { mutableStateOf(false) }

    Scaffold(
        containerColor = Color(0xFFF6F6F6),
        bottomBar = { BottomNavigationBar(navController = navController) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showAddFriendDialog = true },
                containerColor = Color(0xFF2F4B2C)
            ) {
                Icon(
                    imageVector = Icons.Default.PersonAdd,
                    contentDescription = "Tambah teman",
                    tint = Color.White
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = "Sosial",
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp,
                color = Color(0xFF1D1D1D)
            )
            Spacer(modifier = Modifier.height(24.dp))
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(14.dp),
                contentPadding = PaddingValues(bottom = 96.dp)
            ) {
                items(requests, key = { it.id }) { request ->
                    SocialRequestItem(
                        request = request,
                        onMenuClick = { selectedRequest = request }
                    )
                }
            }

            selectedRequest?.let { request ->
                SocialActionDialog(
                    onViewProfile = {
                        profileRequest = request
                        selectedRequest = null
                    },
                    onDelete = {
                        requests.remove(request)
                        selectedRequest = null
                    },
                    onDismiss = { selectedRequest = null }
                )
            }

            if (showAddFriendDialog) {
                AddFriendDialog(
                    onDismiss = { showAddFriendDialog = false },
                    knownEmails = requests.map { it.email }
                )
            }

            profileRequest?.let { request ->
                FriendProfileDialog(
                    request = request,
                    onDismiss = { profileRequest = null }
                )
            }
        }
    }
}

@Composable
private fun SocialRequestItem(
    request: SocialRequest,
    onMenuClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(58.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFF496D38)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_person),
                    contentDescription = "Avatar",
                    modifier = Modifier.size(32.dp)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = request.name,
                    fontFamily = PoppinsFontFamily,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF1C1C1C)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = request.email,
                    fontFamily = PoppinsFontFamily,
                    fontSize = 13.sp,
                    color = Color(0xFF777777)
                )
            }
            IconButton(
                onClick = onMenuClick,
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFF4F7F3E))
            ) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Menu",
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
private fun SocialActionDialog(
    onViewProfile: () -> Unit,
    onDelete: () -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(24.dp))
                .padding(horizontal = 28.dp, vertical = 26.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            ActionButton(
                text = "Lihat Profil",
                background = Color(0xFF4E7A47),
                onClick = onViewProfile
            )
            ActionButton(
                text = "Hapus Pertemanan",
                background = Color(0xFFC34040),
                onClick = onDelete
            )
            ActionButton(
                text = "Kembali",
                background = Color(0xFF203320),
                onClick = onDismiss
            )
        }
    }
}

@Composable
private fun ActionButton(
    text: String,
    background: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(background)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
            .padding(vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontFamily = PoppinsFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            color = Color.White
        )
    }
}

@Composable
private fun AddFriendDialog(
    knownEmails: List<String>,
    onDismiss: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var resultMessage by remember { mutableStateOf<String?>(null) }
    var isSuccess by remember { mutableStateOf<Boolean?>(null) }

    Dialog(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(24.dp))
                .padding(horizontal = 24.dp, vertical = 28.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            Text(
                text = "Cari Teman Anda",
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                color = Color(0xFF1C1C1C)
            )
            TextField(
                value = email,
                onValueChange = {
                    email = it
                    resultMessage = null
                    isSuccess = null
                },
                textStyle = TextStyle(
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                ),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFE9E9E9),
                    unfocusedContainerColor = Color(0xFFE9E9E9),
                    disabledContainerColor = Color(0xFFE9E9E9),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color(0xFF2F4B2C)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(18.dp))
            )
            ActionButton(
                text = "Kirim Pertemanan",
                background = Color(0xFF1F2F1F),
                onClick = {
                    val normalized = email.trim()
                    when {
                        normalized.isEmpty() -> {
                            resultMessage = "Masukkan email terlebih dahulu"
                            isSuccess = false
                        }
                        knownEmails.any { it.equals(normalized, ignoreCase = true) } -> {
                            resultMessage = "Permintaan pertemanan berhasil dikirim"
                            isSuccess = true
                        }
                        else -> {
                            resultMessage = "Pengguna tidak ditemukan"
                            isSuccess = false
                        }
                    }
                }
            )
            resultMessage?.let {
                Text(
                    text = it,
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    color = if (isSuccess == true) Color(0xFF2F4B2C) else Color(0xFFC34040)
                )
            }
            ActionButton(
                text = "Kembali",
                background = Color(0xFF2D3F2C),
                onClick = onDismiss
            )
        }
    }
}


@Composable
private fun FriendProfileDialog(
    request: SocialRequest,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(24.dp))
                .padding(horizontal = 24.dp, vertical = 28.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(76.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFEDEDED)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = request.avatarRes),
                    contentDescription = "Avatar ${request.name}",
                    modifier = Modifier.size(48.dp)
                )
            }
            Text(
                text = request.name,
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                color = Color(0xFF1C1C1C)
            )
            Text(
                text = request.email,
                fontFamily = PoppinsFontFamily,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                textDecoration = TextDecoration.Underline,
                color = Color(0xFF1F2F1F)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(28.dp))
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(PrimaryLightGreen, PrimaryDarkGreen)
                        ))
                    .padding(horizontal = 18.dp, vertical = 20.dp)
            ) {
                BadgeSection(
                    title = "Badge",
                    badges = request.badges
                )
            }
            ActionButton(
                text = "Kembali",
                background = Color(0xFF2D3F2C),
                onClick = onDismiss
            )
        }
    }
}

@Composable
private fun BadgeSection(
    title: String,
    badges: List<FriendBadgeItem>
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                color = Color.White,
                fontSize = 21.sp,
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.SemiBold
            )
        }
        BadgesRow(badges)
    }
}

@Composable
private fun BadgesRow(badges: List<FriendBadgeItem>) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(badges) { badge ->
            BadgeCardItem(
                title = badge.title,
                date = badge.date
            )
        }
    }
}

@Composable
private fun BadgeCardItem(
    title: String,
    date: String
) {
    Card(
        modifier = Modifier
            .width(148.dp)
            .height(153.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0x331E1E1E))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 9.dp, vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Box(
                modifier = Modifier
                    .size(width = 115.dp, height = 70.dp)
                    .background(
                        color = Color(0x331E1E1E),
                        shape = RoundedCornerShape(10.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.superstar_badge),
                    contentDescription = "Badge",
                    modifier = Modifier
                        .width(82.dp)
                        .height(52.dp)
                )
            }
            Text(
                text = title,
                color = Color.White,
                fontSize = 14.sp,
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = date,
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 11.sp,
                fontFamily = PoppinsFontFamily
            )
        }
    }
}

private data class SocialRequest(
    val id: Int,
    val name: String,
    val email: String,
    val avatarRes: Int,
    val badges: List<FriendBadgeItem>
)

private data class FriendBadgeItem(
    val title: String,
    val date: String
)

private fun socialRequests(): List<SocialRequest> = listOf(
    SocialRequest(
        id = 1,
        name = "Tribudi",
        email = "Tribudi969@gmail.com",
        avatarRes = R.drawable.ic_person,
        badges = listOf(
            FriendBadgeItem("Learning", "27 Sept 2025"),
            FriendBadgeItem("Introduction to bla bla bla ...", "27 Sept 2025")
        )
    ),
    SocialRequest(
        id = 2,
        name = "Alika",
        email = "alika@test.com",
        avatarRes = R.drawable.ic_person,
        badges = listOf(
            FriendBadgeItem("UI Research", "02 Okt 2025"),
            FriendBadgeItem("Design Workshop", "10 Okt 2025")
        )
    ),
    SocialRequest(
        id = 3,
        name = "Marcel",
        email = "marcel@test.com",
        avatarRes = R.drawable.ic_person,
        badges = listOf(
            FriendBadgeItem("Mobile Development", "12 Sept 2025")
        )
    )
)

@Preview(showBackground = true)
@Composable
private fun SocialScreenPreview() {
    SocialScreen(navController = rememberNavController())
}

