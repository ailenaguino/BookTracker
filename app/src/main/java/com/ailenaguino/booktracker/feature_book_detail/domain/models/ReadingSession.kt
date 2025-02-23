package com.ailenaguino.booktracker.feature_book_detail.domain.models

data class ReadingSession(
    val id: Int,
    val bookId: Int,
    val readingTime: Int,
    val currentPage: Int,
    val date: String
)
