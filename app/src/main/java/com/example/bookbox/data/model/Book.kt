package com.example.bookbox.data.model

import java.io.Serializable

data class Book(
    val id: String,
    val title: String,
    val authors: String,
    val firstPublishYear: Int?,
    val editionCount: Int,
    val languages: List<String>
) : Serializable