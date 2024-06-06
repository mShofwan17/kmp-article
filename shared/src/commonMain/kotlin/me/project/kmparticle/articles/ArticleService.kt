package me.project.kmparticle.articles

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import me.project.kmparticle.articles.models.ArticleDTO
import me.project.kmparticle.articles.models.ArticlesResponse

class ArticleService(private val httpClient: HttpClient) {
    private val country = "us"
    private val category = "business"
    private val apiKey = "c59579959d7743ceafafd30b42122525"

    suspend fun fetchArticles(): List<ArticleDTO> {
        val response = httpClient.get("https://newsapi.org/v2/top-headlines?country=$country&category=$category&apiKey=$apiKey")
                .body<ArticlesResponse>()

        return response.articles

    }
}