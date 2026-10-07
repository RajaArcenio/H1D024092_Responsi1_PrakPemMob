package com.example.bookbox.data.remote

import com.example.bookbox.data.model.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenLibraryApi {
    @GET("search.json")
    suspend fun searchBooks(
        @Query("q") query: String,
        @Query("limit") limit: Int = 20,
        @Query("fields") fields: String =
            "key,title,author_name,first_publish_year,edition_count,language"
    ): SearchResponse
}