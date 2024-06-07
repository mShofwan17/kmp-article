package me.project.kmparticle.articles

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.project.kmparticle.BaseViewModel
import me.project.kmparticle.articles.models.ArticleState

class ArticlesViewModel(
    private val getArticlesUseCase: GetArticlesUseCase
) : BaseViewModel() {

    private val _articleState: MutableStateFlow<ArticleState> =
        MutableStateFlow(ArticleState(loading = true))
    val articleState get() = _articleState.asStateFlow()

    init {
        getArticles()
    }

    private fun getArticles() {
        scope.launch {
            delay(2500)
            val fetchArticle = getArticlesUseCase()
            _articleState.update {
                it.copy(articles = fetchArticle, loading = false)
            }
        }
    }


}