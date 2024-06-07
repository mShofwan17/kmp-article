package me.project.kmparticle.di

import me.project.kmparticle.articles.di.articleModule

val sharedModule = listOf(
    networkModule,
    articleModule
)