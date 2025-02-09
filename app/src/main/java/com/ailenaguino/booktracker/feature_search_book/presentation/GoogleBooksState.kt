package com.ailenaguino.booktracker.feature_search_book.presentation

import com.ailenaguino.booktracker.feature_search_book.domain.models.GoogleBook

data class GoogleBooksState(
    val isLoading: Boolean = false,
    val books: List<GoogleBook> = emptyList(),
    val error: String = ""
)
