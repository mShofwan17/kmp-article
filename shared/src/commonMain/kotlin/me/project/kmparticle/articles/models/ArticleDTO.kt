package me.project.kmparticle.articles.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import me.project.kmparticle.getDaysAgo

@Serializable
data class ArticleDTO(
    @SerialName("author")
    val author: String? = null,
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String?,
    @SerialName("url")
    val url: String? = null,
    @SerialName("urlToImage")
    val urlToImage: String?,
    @SerialName("publishedAt")
    val publishedAt: String?,
    @SerialName("content")
    val content: String? = null,
) {
    fun toArticle(): Article {
        return Article(
            title = title,
            desc = description,
            date = publishedAt?.getDaysAgo(),
            imageUrl = urlToImage
        )
    }
}
