package me.project.kmparticle.android.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import me.project.kmparticle.android.screens.about.AboutScreen
import me.project.kmparticle.android.screens.articles.ArticlesScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(navController = navController, startDestination = Screens.ARTICLES.route) {
        composable(Screens.ARTICLES.route) {
            ArticlesScreen(
                navHostController = navController
            )
        }

        composable(Screens.ABOUT_DEVICE.route) {
            AboutScreen(
                navHostController = navController
            )
        }
    }
}