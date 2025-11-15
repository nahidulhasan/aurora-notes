package com.aurora.notes.di

import com.aurora.notes.config.AppConfig
import com.aurora.notes.controllers.AuthController
import com.aurora.notes.controllers.NoteController
import com.aurora.notes.repository.NoteRepository
import com.aurora.notes.services.AuthService
import com.aurora.notes.services.NoteService
import org.koin.dsl.module

fun koinModule(config: AppConfig) = module {
    single { config }
    single { NoteRepository() }
    single { NoteService(get()) }
    single { NoteController(get()) }

    single { AuthService(get()) }
    // AuthController needs AuthService and AppConfig
    single { AuthController(get(), get()) }
}
