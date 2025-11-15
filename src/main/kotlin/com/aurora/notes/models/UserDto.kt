package com.aurora.notes.models

import kotlinx.serialization.Serializable

@Serializable
data class UserDto(val id: Int, val username: String)

data class UserPrincipal(val id: Int)