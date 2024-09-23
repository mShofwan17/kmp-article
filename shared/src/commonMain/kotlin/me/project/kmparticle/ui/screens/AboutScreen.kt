package me.project.kmparticle.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import me.project.kmparticle.Platform


class AboutScreen : Screen {
    @Composable
    override fun Content() {
        AboutScreenContent()
    }
}

@Composable
fun AboutScreenContent() {
    val navigator = LocalNavigator.currentOrThrow

    Column {
        Toolbar(
            onBackButton = { navigator.pop() }
        )
        ContentView()
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Toolbar(
    onBackButton: () -> Unit
) {
    TopAppBar(
        title = { Text(text = "About Device") },
        navigationIcon = {
            IconButton(onClick = { onBackButton.invoke() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "icBack")
            }
        }
    )
}

@Composable
private fun ContentView() {
    val items = makeItems()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 8.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
    ) {
        items(items.size) {
            val item = items[it]
            RowView(title = item.first, subtitle = item.second)
        }
    }
}

@Composable
private fun RowView(title: String, subtitle: String) {
    Column(Modifier.padding(top = 8.dp, bottom = 8.dp)) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray
        )
        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodyLarge
        )
        Divider(Modifier.padding(top = 8.dp))
    }

}

private fun makeItems(): List<Pair<String, String>> {
    val platform = Platform()
    platform.logSystemInfo()

    return listOf(
        Pair("Operating System", "${platform.osName} ${platform.osVersion}"),
        Pair("Device", platform.deviceModel),
        Pair("Density", platform.density.toString())
    )
}
