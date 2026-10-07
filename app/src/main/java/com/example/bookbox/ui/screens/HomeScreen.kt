package com.example.bookbox.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bookbox.data.model.Book
import com.example.bookbox.ui.BookUiState
import com.example.bookbox.ui.BookViewModel
import com.example.bookbox.ui.components.BookItem
import com.example.bookbox.ui.components.BookSearchBar
import com.example.bookbox.ui.components.EmptyView
import com.example.bookbox.ui.components.ErrorView
import com.example.bookbox.ui.components.LoadingView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: BookViewModel, onBookClick: (Book) -> Unit) {
    val uiState by viewModel.uiState.collectAsState()
    val query by viewModel.query.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("BookBox", style = MaterialTheme.typography.titleLarge)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { padding ->
        Column(Modifier.padding(padding)) {
            BookSearchBar(
                query = query,
                onQueryChange = viewModel::onQueryChange,
                onSearch = viewModel::search
            )
            when (val state = uiState) {
                is BookUiState.Loading -> LoadingView()
                is BookUiState.Error -> ErrorView(state.message, onRetry = viewModel::search)
                is BookUiState.Success ->
                    if (state.books.isEmpty()) EmptyView()
                    else BookList(state.books, onBookClick)
            }
        }
    }
}

@Composable
private fun BookList(books: List<Book>, onBookClick: (Book) -> Unit) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(books, key = { it.id }) { book ->
            BookItem(book = book, onClick = { onBookClick(book) })
        }
    }
}