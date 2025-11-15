package com.aurora.notes.controllers

import com.aurora.notes.config.AppConfig
import com.aurora.notes.security.JwtProvider
import com.aurora.notes.services.AuthService

class AuthController(private val service: AuthService, private val cfg: AppConfig) {
    fun login(username: String, password: String): String? {
        val id = service.verifyUser(username, password) ?: return null
        return JwtProvider.generateToken(id, cfg.jwt)
    }
}
