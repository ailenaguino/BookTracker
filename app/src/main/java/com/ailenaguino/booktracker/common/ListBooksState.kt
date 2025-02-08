package com.ailenaguino.booktracker.common

import com.ailenaguino.booktracker.feature_add_book.domain.models.Book

data class ListBooksState(
    val isLoading: Boolean = false,
    val books: List<Book> = emptyList(),
    val error: String = ""
)
