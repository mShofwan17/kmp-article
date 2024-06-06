package me.project.kmparticle.articles

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import me.project.kmparticle.BaseViewModel
import me.project.kmparticle.articles.models.ArticleState

class ArticlesViewModel : BaseViewModel() {

    private val _articleState: MutableStateFlow<ArticleState> =
        MutableStateFlow(ArticleState(loading = true))
    val articleState get() = _articleState.asStateFlow()
    private val getArticlesUseCase: GetArticlesUseCase

    init {
        val httpClient = HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
        }
        getArticlesUseCase = GetArticlesUseCase(ArticleService(httpClient))

        getArticles()
    }

    private fun getArticles() {
        scope.launch {
            delay(2500)
            //_articleState.emit(ArticleState(errorMsg = "Something's Wrong", loading = false))
            val fetchArticle = getArticlesUseCase()
            _articleState.update {
                it.copy(articles = fetchArticle, loading = false)
            }
        }
    }


}