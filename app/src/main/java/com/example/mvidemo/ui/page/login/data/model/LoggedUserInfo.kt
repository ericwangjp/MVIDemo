package com.example.mvidemo.ui.page.login.data.model

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class LoggedUserInfo(
    val userId: String,
    val displayName: String
)