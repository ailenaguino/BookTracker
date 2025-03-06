package com.ailenaguino.booktracker.feature_add_book.domain.models

data class Book(
    val id: Int?,
    val title: String,
    val author: String,
    var cover: String,
    val totalPages: Int,
    val typeBook: String,
    val registerProgress: String,
    var state: String,
    var currentPage: Int = 0,
    var startedDay: String = "",
    var sessions: Int = 0
)
