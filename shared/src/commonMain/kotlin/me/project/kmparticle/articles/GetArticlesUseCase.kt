package me.project.kmparticle.articles

import me.project.kmparticle.articles.models.Article
import me.project.kmparticle.articles.repository.ArticlesRepository
import me.project.kmparticle.articles.source.ArticleService

class GetArticlesUseCase(private val repository: ArticlesRepository) {
    suspend operator fun invoke(forceFetch: Boolean): List<Article> {
        val response = repository.getArticles(forceFetch)
        return response.map { it.toArticle() }
    }
}