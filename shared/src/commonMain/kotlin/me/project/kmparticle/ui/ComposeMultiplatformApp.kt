package me.project.kmparticle.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import me.project.kmparticle.ui.screens.ArticlesScreen

@Composable
fun App(modifier: Modifier = Modifier) {
    MyApplicationTheme {
        Surface(
            modifier = modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Navigator(
                ArticlesScreen()
            ) { navigator ->
                SlideTransition(navigator = navigator)
            }
        }
    }
}