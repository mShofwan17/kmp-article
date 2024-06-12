package me.project.kmparticle.di

import app.cash.sqldelight.db.SqlDriver
import me.project.kmparticle.db.DatabaseDriverFactory
import me.project.kmparticle.db.KmpArticleDatabase
import org.koin.dsl.module

val databaseModule = module {
    single<SqlDriver> { DatabaseDriverFactory().createDriver() }
    single { KmpArticleDatabase(get()) }
}