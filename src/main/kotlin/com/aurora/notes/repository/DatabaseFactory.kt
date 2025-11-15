package com.aurora.notes.repository

import com.aurora.notes.config.AppConfig
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.transactions.transaction

object NotesTable : Table("notes") {
    val id = integer("id").autoIncrement()
    val title = varchar("title", 255)
    val content = varchar("content", 2000)
    override val primaryKey = PrimaryKey(id)
}

object DatabaseFactory {
    fun init(cfg: AppConfig) {
        val hikariConfig = HikariConfig().apply {
            jdbcUrl = cfg.db.jdbcUrl
            username = cfg.db.user
            password = cfg.db.password
            maximumPoolSize = 3
        }
        val ds = HikariDataSource(hikariConfig)
        Database.connect(ds)
        transaction {
            SchemaUtils.create(NotesTable)
        }
    }
}
