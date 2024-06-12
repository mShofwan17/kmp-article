package me.project.kmparticle.articles.source

import me.project.kmparticle.articles.models.ArticleDTO
import me.project.kmparticle.db.KmpArticleDatabase

class ArticlesDataSource(private val database: KmpArticleDatabase) {
    fun getAllArticles(): List<ArticleDTO> {
        return database.kmpArticleDatabaseQueries.selectAllArticle().executeAsList().map {
            ArticleDTO(
                title = it.title,
                description = it.desc,
                publishedAt = it.date,
                urlToImage = it.imageUrl
            )
        }
    }

    fun insertArticles(articles: List<ArticleDTO>) {
        database.kmpArticleDatabaseQueries.transaction {
            articles.forEach { article ->
                database.kmpArticleDatabaseQueries.insertArticle(
                    title = article.title,
                    desc = article.description,
                    date = article.publishedAt ?: "",
                    imageUrl = article.urlToImage
                )
            }
        }
    }

    fun clearArticles() = database.kmpArticleDatabaseQueries.removeAllArticle()
}