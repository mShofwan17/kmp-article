package me.project.kmparticle.articles.di

import me.project.kmparticle.articles.source.ArticleService
import me.project.kmparticle.articles.ArticlesViewModel
import me.project.kmparticle.articles.GetArticlesUseCase
import me.project.kmparticle.articles.repository.ArticlesRepository
import me.project.kmparticle.articles.source.ArticlesDataSource
import org.koin.dsl.module

val articleModule = module {
    single { ArticleService(get()) }
    single { GetArticlesUseCase(get()) }
    single { ArticlesViewModel(get()) }
    single { ArticlesDataSource(get()) }
    single { ArticlesRepository(get(), get()) }
}