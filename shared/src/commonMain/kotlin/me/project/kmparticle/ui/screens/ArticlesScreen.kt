package me.project.kmparticle.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import io.ktor.http.Url
import me.project.kmparticle.articles.ArticlesViewModel
import me.project.kmparticle.articles.models.Article
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject


class ArticlesScreen : Screen {
    @Composable
    override fun Content() {
        ArticlesScreenContent()
    }
}

@Composable
fun ArticlesScreenContent(
    viewModel: ArticlesViewModel = koinInject()
) {
    val articleState = viewModel.articleState.collectAsState()

    Column {
        AppBar()
        articleState.value.errorMsg?.let {
            ErrorMessage(message = it)
        }
        if (articleState.value.articles.isNotEmpty()) ArticleListView(viewModel)

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBar() {
    val navigator = LocalNavigator.currentOrThrow

    TopAppBar(
        title = { Text(text = "Articles") },
        actions = {
            IconButton(onClick = {
                navigator.push(AboutScreen())
            }) {
                Icon(imageVector = Icons.Outlined.Info, contentDescription = "icInfo")
            }
        }
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ArticleListView(viewModel: ArticlesViewModel) {

    val stateSwipeRefresh = rememberPullRefreshState(
        refreshing = viewModel.articleState.value.loading,
        onRefresh = { viewModel.getArticles(true) }
    )

    Box(
        modifier = Modifier
            .pullRefresh(state = stateSwipeRefresh)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(viewModel.articleState.value.articles.size) {
                val item = viewModel.articleState.value.articles[it]
                ArticleItem(item = item)
            }
        }

        PullRefreshIndicator(
            refreshing = viewModel.articleState.value.loading,
            state = stateSwipeRefresh,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}

@Composable
fun ArticleItem(item: Article) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        KamelImage(
            resource = asyncPainterResource(data = Url(item.imageUrl ?: "")),
            contentDescription = "articleImage",
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = item.title,
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 22.sp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = item.desc ?: "")
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = item.date ?: "",
            style = TextStyle(color = Color.Gray),
            modifier = Modifier.align(Alignment.End)
        )
        Spacer(modifier = Modifier.height(4.dp))
    }
}

@Composable
fun ErrorMessage(message: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            style = TextStyle(fontSize = 20.sp, textAlign = TextAlign.Center)
        )
    }
}