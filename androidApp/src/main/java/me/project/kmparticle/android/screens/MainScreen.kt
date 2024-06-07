package me.project.kmparticle.android.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import me.project.kmparticle.android.navigation.NavGraph

@Composable
fun AppMain() {
    val navController = rememberNavController()
    Scaffold {
        NavGraph(
            navController = navController,
            modifier = Modifier.padding(it)
        )
    }
}

