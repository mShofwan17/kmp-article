package me.project.kmparticle.android.di

import app.cash.sqldelight.db.SqlDriver
import me.project.kmparticle.db.DatabaseDriverFactory
import me.project.kmparticle.db.KmpArticleDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single<SqlDriver> { DatabaseDriverFactory(androidContext()).createDriver() }
    single<KmpArticleDatabase> { KmpArticleDatabase(get()) }
}