package com.aurora.notes.models

import org.jetbrains.exposed.dao.id.IntIdTable

object NotesTable : IntIdTable("notes") {
    val title = varchar("title", 255)
    val content = text("content")
}

