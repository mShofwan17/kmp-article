package me.project.kmparticle.di

import me.project.kmparticle.articles.ArticlesViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

fun initKoin() {
    val modules = sharedModule
    startKoin { modules(modules) }
}


class ArticleInjector : KoinComponent {
    val articlesViewModel: ArticlesViewModel by inject()
}