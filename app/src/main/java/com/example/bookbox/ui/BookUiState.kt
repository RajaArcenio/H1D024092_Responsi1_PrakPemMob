package com.example.bookbox.ui

import com.example.bookbox.data.model.Book

sealed interface BookUiState {
    data object Loading : BookUiState
    data class Success(val books: List<Book>) : BookUiState
    data class Error(val message: String) : BookUiState
}