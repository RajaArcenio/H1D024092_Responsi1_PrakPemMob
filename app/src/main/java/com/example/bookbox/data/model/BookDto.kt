package com.example.bookbox.data.model

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("docs") val docs: List<BookDto>? = null
)

data class BookDto(
    @SerializedName("key") val key: String? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("author_name") val authorName: List<String>? = null,
    @SerializedName("first_publish_year") val firstPublishYear: Int? = null,
    @SerializedName("edition_count") val editionCount: Int? = null,
    @SerializedName("language") val language: List<String>? = null
)