package com.aurora.notes.services

import com.aurora.notes.config.AppConfig

class AuthService(private val cfg: AppConfig) {
    // Replace with real user lookup
    fun verifyUser(username: String, password: String): Int? {
        return if (username.isNotBlank() && password.isNotBlank()) 1 else null
    }
}
