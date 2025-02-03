package com.ailenaguino.booktracker.feature_add_book.domain.models

data class Book(
    val title: String,
    val author: String,
    var cover: String,
    val totalPages: Int,
    val typeBook: String,
    val registerProgress: String,
    val progress: String
)
