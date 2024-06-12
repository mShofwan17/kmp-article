package me.project.kmparticle.articles.repository

import me.project.kmparticle.articles.source.ArticleService
import me.project.kmparticle.articles.source.ArticlesDataSource
import me.project.kmparticle.articles.models.ArticleDTO

class ArticlesRepository(
    private val dataSource: ArticlesDataSource,
    private val service: ArticleService
) {
    suspend fun getArticles(forceFetch: Boolean): List<ArticleDTO> {
        if (forceFetch) {
            dataSource.clearArticles()
            return fetchArticles()
        }
        val articlesDb = dataSource.getAllArticles()
        if (articlesDb.isEmpty()) return fetchArticles()

        return articlesDb
    }

    private suspend fun fetchArticles(): List<ArticleDTO> {
        val fetchArticles = service.fetchArticles()
        dataSource.insertArticles(fetchArticles)
        return fetchArticles
    }
}