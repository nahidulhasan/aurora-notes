package com.aurora.notes.security

import com.aurora.notes.config.JwtConfig
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm

object JwtProvider {
    fun generateToken(userId: Int, cfg: JwtConfig): String = JWT.create()
        .withIssuer(cfg.issuer)
        .withAudience(cfg.audience)
        .withClaim("userId", userId)
        .sign(Algorithm.HMAC256(cfg.secret))

    fun verifier(cfg: JwtConfig) = JWT
        .require(Algorithm.HMAC256(cfg.secret))
        .withIssuer(cfg.issuer)
        .withAudience(cfg.audience)
        .build()
}
