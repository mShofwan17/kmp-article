package me.project.kmparticle.articles

data class ArticleState(
    val articles: List<Article> = emptyList(),
    val loading: Boolean = false,
    val errorMsg: String? = null
)