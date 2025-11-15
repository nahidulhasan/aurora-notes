package com.aurora.notes.services

import com.aurora.notes.models.NoteDto
import com.aurora.notes.repository.NoteRepository

class NoteService(private val repo: NoteRepository) {
    fun list(): List<NoteDto> = repo.all()
    fun get(id: Int): NoteDto? = repo.find(id)
    fun create(title: String, content: String): NoteDto = repo.create(title, content)
}
