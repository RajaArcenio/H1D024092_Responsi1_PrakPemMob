package com.example.bookbox.data.repository

import com.example.bookbox.data.model.Book
import com.example.bookbox.data.model.toBook
import com.example.bookbox.data.remote.OpenLibraryApi
import kotlin.coroutines.cancellation.CancellationException

class BookRepository(private val api: OpenLibraryApi) {

    suspend fun searchBooks(query: String): Result<List<Book>> = try {
        val books = api.searchBooks(query).docs.orEmpty().mapNotNull { it.toBook() }
        Result.success(books)
    } catch (e: CancellationException) {
        throw e
    } catch (e: Exception) {
        Result.failure(e)
    }
}