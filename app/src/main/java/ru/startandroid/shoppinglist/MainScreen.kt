package ru.startandroid.shoppinglist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import ru.startandroid.core.navigation.NavContext
import ru.startandroid.core.navigation.NavigateTo

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel(),
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        val navController = rememberNavController()
        Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            NavHost(
                navController = navController,
                startDestination = HomeNavScreen.HomeRoute
            ) {
                val navContext = NavContext(navController, this)
                viewModel.navScreens.forEach { navScreen ->
                    navScreen.addToGraph(navContext)
                }
            }
        }
        LaunchedEffect(Unit) {
            viewModel.navigator.events.collect { event ->
                when (event) {
                    is NavigateTo -> navController.navigate(event.route)
                    else -> Unit
                }
            }
        }
    }
}