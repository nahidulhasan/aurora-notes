package com.aurora.notes.controllers

import com.aurora.notes.models.NoteDto
import com.aurora.notes.services.NoteService

class NoteController(private val service: NoteService) {
    fun list(): List<NoteDto> = service.list()
    fun get(id: Int): NoteDto? = service.get(id)
    fun create(title: String, content: String): NoteDto = service.create(title, content)
}
