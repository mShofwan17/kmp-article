package me.project.kmparticle.android.screens.articles

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import me.project.kmparticle.android.navigation.Screens
import me.project.kmparticle.articles.ArticlesViewModel
import me.project.kmparticle.articles.models.Article
import org.koin.androidx.compose.getViewModel

@Composable
fun ArticlesScreen(
    navHostController: NavHostController,
    viewModel: ArticlesViewModel = getViewModel()
) {
    val articleState = viewModel.articleState.collectAsState()

    Column {
        AppBar(onAboutButton = { navHostController.navigate(Screens.ABOUT_DEVICE.route) })
        if (articleState.value.loading) Loader()
        articleState.value.errorMsg?.let {
            ErrorMesssage(message = it)
        }
        if (articleState.value.articles.isNotEmpty()) ArticleListView(articles = articleState.value.articles)

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBar(
    onAboutButton: () -> Unit
) {
    TopAppBar(
        title = { Text(text = "Articles") },
        actions = {
            IconButton(onClick = onAboutButton) {
                Icon(imageVector = Icons.Outlined.Info, contentDescription = "icInfo")
            }
        }
    )
}

@Composable
fun ArticleListView(articles: List<Article>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(articles.size) {
            val item = articles[it]
            ArticleItem(item = item)
        }
    }
}

@Composable
fun ArticleItem(item: Article) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxWidth(),
            model = item.imageUrl,
            contentScale = ContentScale.Crop,
            contentDescription = "imageUrl"
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
fun Loader() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = MaterialTheme.colorScheme.surfaceVariant,
            trackColor = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
fun ErrorMesssage(message: String) {
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