package com.example.bookbox.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bookbox.data.model.Book
import com.example.bookbox.ui.BookViewModel
import com.example.bookbox.ui.screens.DetailScreen
import com.example.bookbox.ui.screens.HomeScreen

private object Routes {
    const val HOME = "home"
    const val DETAIL = "detail"
    const val ARG_BOOK = "book"
}

@Composable
fun AppNavHost(viewModel: BookViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.HOME) {
        composable(Routes.HOME) {
            HomeScreen(
                viewModel = viewModel,
                onBookClick = { book ->
                    navController.currentBackStackEntry
                        ?.savedStateHandle?.set(Routes.ARG_BOOK, book)
                    navController.navigate(Routes.DETAIL)
                }
            )
        }
        composable(Routes.DETAIL) {
            val book = remember {
                navController.previousBackStackEntry
                    ?.savedStateHandle?.get<Book>(Routes.ARG_BOOK)
            }
            DetailScreen(book = book, onBack = { navController.popBackStack() })
        }
    }
}