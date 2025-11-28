package com.example.learnexus.ui.notification

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.learnexus.ui.theme.PoppinsFontFamily
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationScreen(
    userId: String,
    onBackClick: () -> Unit = {}
) {
    val notifications = remember { mutableStateListOf(*sampleNotifications().toTypedArray()) }
    var selectedNotification by remember { mutableStateOf<NotificationUiModel?>(null) }

    NotificationContent(
        notifications = notifications,
        onBackClick = onBackClick,
        onMenuClick = { selectedNotification = it },
        onAcceptRequest = { notification ->
            val index = notifications.indexOfFirst { it.id == notification.id }
            if (index != -1) {
                notifications[index] = notifications[index].copy(
                    highlighted = false,
                    isFriendRequest = false,
                    message = "Berhasil berteman"
                )
            }
            selectedNotification = null
        },
        onRejectRequest = { notification ->
            val index = notifications.indexOfFirst { it.id == notification.id }
            if (index != -1) {
                notifications.removeAt(index)
            }
            selectedNotification = null
        },
        onDismissDialog = { selectedNotification = null },
        selectedNotification = selectedNotification
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NotificationContent(
    notifications: List<NotificationUiModel>,
    onBackClick: () -> Unit,
    onMenuClick: (NotificationUiModel) -> Unit,
    onAcceptRequest: (NotificationUiModel) -> Unit,
    onRejectRequest: (NotificationUiModel) -> Unit,
    onDismissDialog: () -> Unit,
    selectedNotification: NotificationUiModel?
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)),
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent)
                    .padding(top = 40.dp, start = 24.dp, bottom = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF294C2D))
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Kembali",
                        tint = Color.White
                    )
                }
                Spacer(modifier = Modifier.size(16.dp))
                Text(
                    text = "Notifikasi",
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color(0xFF1C1C1C)
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF8F8F8))
                .padding(paddingValues)
        ) {
            NotificationList(
                notifications = notifications,
                modifier = Modifier.fillMaxSize(),
                onMenuClick = onMenuClick
            )

            selectedNotification?.let { notif ->
                ActionDialog(
                    onAccept = { onAcceptRequest(notif) },
                    onReject = { onRejectRequest(notif) },
                    onDismiss = onDismissDialog
                )
            }
        }
    }
}

@Composable
private fun NotificationList(
    notifications: List<NotificationUiModel>,
    modifier: Modifier = Modifier,
    onMenuClick: (NotificationUiModel) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(notifications) { notification ->
            NotificationItem(
                notification = notification,
                onMenuClick = onMenuClick
            )
        }
    }
}

@Composable
private fun NotificationItem(
    notification: NotificationUiModel,
    onMenuClick: (NotificationUiModel) -> Unit
) {
    val highlightColor = Color(0xFFE5F8D9)
    val defaultColor = Color.White
    val backgroundColor = if (notification.highlighted) highlightColor else defaultColor

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProfileAvatar(
                modifier = Modifier.size(52.dp)
            )
            Spacer(modifier = Modifier.size(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = notification.senderName,
                        fontFamily = PoppinsFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        color = Color(0xFF1B1B1B),
                        modifier = Modifier.weight(1f),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = notification.timeAgo,
                        fontFamily = PoppinsFontFamily,
                        fontSize = 12.sp,
                        color = Color(0xFF7A7A7A)
                    )
                }
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = notification.message,
                    fontFamily = PoppinsFontFamily,
                    fontSize = 13.sp,
                    color = Color(0xFF4B4B4B),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(modifier = Modifier.size(12.dp))
            if (notification.isFriendRequest) {
                IconButton(onClick = { onMenuClick(notification) }) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "Lainnya",
                        tint = Color(0xFF5A5A5A)
                    )
                }
            }
        }
    }
}

@Composable
private fun ActionDialog(
    onAccept: () -> Unit,
    onReject: () -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(20.dp))
                .padding(horizontal = 24.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            RoundedButton(
                text = "Terima Pertemanan",
                background = Color(0xFF406D3C),
                onClick = onAccept
            )
            RoundedButton(
                text = "Tolak",
                background = Color(0xFFC34242),
                onClick = onReject
            )
            RoundedButton(
                text = "Kembali",
                background = Color(0xFF1C301C),
                onClick = onDismiss
            )
        }
    }
}

@Composable
private fun RoundedButton(
    text: String,
    background: Color,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(background)
            .clickable(
                interactionSource = interactionSource,
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
private fun ProfileAvatar(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(Color(0xFFB1D4B2)),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = "Avatar",
            tint = Color.White,
            modifier = Modifier.size(28.dp)
        )
    }
}

data class NotificationUiModel(
    val id: String,
    val senderName: String,
    val message: String,
    val timeAgo: String,
    val highlighted: Boolean = false,
    val isFriendRequest: Boolean = false
)

private fun sampleNotifications(): List<NotificationUiModel> = listOf(
    NotificationUiModel(
        id = UUID.randomUUID().toString(),
        senderName = "Tribudi",
        message = "Berhasil berteman",
        timeAgo = "3 menit",
        highlighted = false,
        isFriendRequest = false
    ),
    NotificationUiModel(
        id = UUID.randomUUID().toString(),
        senderName = "Tribudi",
        message = "Berhasil berteman",
        timeAgo = "3 Hari",
        highlighted = false,
        isFriendRequest = false
    ),
    NotificationUiModel(
        id = UUID.randomUUID().toString(),
        senderName = "Tribudi",
        message = "Mengirim permintaan berteman",
        timeAgo = "3 Jam",
        highlighted = true,
        isFriendRequest = true
    )
)

@Preview(showBackground = true)
@Composable
private fun NotificationScreenPreview() {
    NotificationContent(
        notifications = sampleNotifications(),
        onBackClick = {},
        onMenuClick = {},
        onAcceptRequest = {},
        onRejectRequest = {},
        onDismissDialog = {},
        selectedNotification = null
    )
}