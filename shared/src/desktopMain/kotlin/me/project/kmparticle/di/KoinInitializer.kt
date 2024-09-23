package me.project.kmparticle.di

import org.koin.core.context.startKoin

fun initKoin() = startKoin {
    modules(
        sharedModule + databaseModule
    )
}.koin