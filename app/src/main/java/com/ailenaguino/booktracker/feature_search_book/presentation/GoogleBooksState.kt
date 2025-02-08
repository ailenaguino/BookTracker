package com.ailenaguino.booktracker.feature_search_book.presentation

import com.ailenaguino.booktracker.feature_search_book.domain.models.Book

data class GoogleBooksState(
    val isLoading: Boolean = false,
    val books: List<Book> = emptyList(),
    val error: String = ""
)
