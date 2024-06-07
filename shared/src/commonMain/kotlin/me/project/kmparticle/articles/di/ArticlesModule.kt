package me.project.kmparticle.articles.di

import me.project.kmparticle.articles.ArticleService
import me.project.kmparticle.articles.ArticlesViewModel
import me.project.kmparticle.articles.GetArticlesUseCase
import org.koin.dsl.module

val articleModule = module {
    single { ArticleService(get()) }
    single { GetArticlesUseCase(get()) }
    single { ArticlesViewModel(get()) }
}