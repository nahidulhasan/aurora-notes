package com.aurora.notes.repository

import com.aurora.notes.models.NoteDto
import com.aurora.notes.models.NotesTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class NoteRepository {

    fun all(): List<NoteDto> = transaction {
        // selectAll() -> Query (new DSL). No slice() needed for all columns.
        NotesTable
            .selectAll()
            .map { row ->
                NoteDto(
                    id = row[NotesTable.id].value,
                    title = row[NotesTable.title],
                    content = row[NotesTable.content]
                )
            }
    }

    fun find(id: Int): NoteDto? = transaction {
        // Use selectAll().where { ... } (recommended new DSL) and limit to 1.
        NotesTable
            .selectAll()
            .where { NotesTable.id eq id }
            .limit(1)
            .firstOrNull()
            ?.let { row ->
                NoteDto(
                    id = row[NotesTable.id].value,
                    title = row[NotesTable.title],
                    content = row[NotesTable.content]
                )
            }
    }

    fun create(title: String, content: String): NoteDto = transaction {
        val id = NotesTable.insertAndGetId { row ->
            row[NotesTable.title] = title
            row[NotesTable.content] = content
        }.value

        NoteDto(id, title, content)
    }
}
