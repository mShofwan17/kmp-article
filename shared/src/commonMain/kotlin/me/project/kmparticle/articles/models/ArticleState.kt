package me.project.kmparticle.articles.models

data class ArticleState(
    val articles: List<Article> = emptyList(),
    val loading: Boolean = false,
    val errorMsg: String? = null
)