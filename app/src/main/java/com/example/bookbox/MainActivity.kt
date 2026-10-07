package com.example.bookbox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookbox.ui.BookViewModel
import com.example.bookbox.ui.navigation.AppNavHost
import com.example.bookbox.ui.theme.BookBoxTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BookBoxTheme {
                val bookViewModel: BookViewModel = viewModel()
                AppNavHost(bookViewModel)
            }
        }
    }
}