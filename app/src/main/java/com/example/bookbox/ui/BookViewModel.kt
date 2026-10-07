package com.example.bookbox.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookbox.data.remote.RetrofitClient
import com.example.bookbox.data.repository.BookRepository
import com.example.bookbox.util.toUserMessage
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BookViewModel(
    private val repository: BookRepository = BookRepository(RetrofitClient.api)
) : ViewModel() {

    private val _query = MutableStateFlow("indonesia")
    val query: StateFlow<String> = _query.asStateFlow()

    private val _uiState = MutableStateFlow<BookUiState>(BookUiState.Loading)
    val uiState: StateFlow<BookUiState> = _uiState.asStateFlow()

    private var searchJob: Job? = null

    init { search() }

    fun onQueryChange(newQuery: String) {
        _query.value = newQuery
    }

    fun search() {
        val keyword = _query.value.trim()
        if (keyword.isEmpty()) {
            _uiState.value = BookUiState.Error("Kata kunci tidak boleh kosong.")
            return
        }
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            _uiState.value = BookUiState.Loading
            repository.searchBooks(keyword)
                .onSuccess { _uiState.value = BookUiState.Success(it) }
                .onFailure { _uiState.value = BookUiState.Error(it.toUserMessage()) }
        }
    }
}