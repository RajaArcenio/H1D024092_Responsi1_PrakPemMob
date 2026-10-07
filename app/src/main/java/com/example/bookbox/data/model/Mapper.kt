package com.example.bookbox.data.model

fun BookDto.toBook(): Book? {
    val safeKey = key ?: return null
    val safeTitle = title?.takeIf { it.isNotBlank() } ?: return null
    return Book(
        id = safeKey.substringAfterLast('/'),
        title = safeTitle,
        authors = authorName?.joinToString(", ") ?: "Penulis tidak diketahui",
        firstPublishYear = firstPublishYear,
        editionCount = editionCount ?: 0,
        languages = language.orEmpty()
    )
}