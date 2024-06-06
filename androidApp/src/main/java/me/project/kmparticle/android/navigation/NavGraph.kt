package me.project.kmparticle.android.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import me.project.kmparticle.android.screens.about.AboutScreen
import me.project.kmparticle.android.screens.articles.ArticlesScreen
import me.project.kmparticle.articles.ArticlesViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier,
    articlesViewModel: ArticlesViewModel
) {
    NavHost(navController = navController, startDestination = Screens.ARTICLES.route) {
        composable(Screens.ARTICLES.route) {
            ArticlesScreen(
                navHostController = navController,
                viewModel = articlesViewModel
            )
        }

        composable(Screens.ABOUT_DEVICE.route) {
            AboutScreen(
                navHostController = navController
            )
        }
    }
}