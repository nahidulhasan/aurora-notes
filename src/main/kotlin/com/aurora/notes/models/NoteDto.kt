package com.aurora.notes.models

import kotlinx.serialization.Serializable

@Serializable
data class NoteDto(
    val id: Int,
    val title: String,
    val content: String
)
