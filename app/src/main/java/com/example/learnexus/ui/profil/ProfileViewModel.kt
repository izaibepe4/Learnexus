package com.example.learnexus.ui.profil

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

data class ProfileUiState(
    val name: String = "Arap Mujaer",
    val email: String = "araparap.mujaer@gmail.com",
    val followers: Int = 27,
    val following: Int = 27
)

class ProfileViewModel : ViewModel() {
    var uiState by mutableStateOf(ProfileUiState())
        private set

    fun updateName(newName: String) {
        if (newName.isNotBlank()) {
            uiState = uiState.copy(name = newName)
        }
    }
}


