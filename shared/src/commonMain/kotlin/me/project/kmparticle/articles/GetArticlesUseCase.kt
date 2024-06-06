package me.project.kmparticle.articles

import me.project.kmparticle.articles.models.Article

class GetArticlesUseCase(private val service: ArticleService) {
    suspend operator fun invoke(): List<Article>{
        val response = service.fetchArticles()
        return response.map { it.toArticle() }
    }
}