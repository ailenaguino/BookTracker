package com.ailenaguino.booktracker.common

import com.ailenaguino.booktracker.feature_add_book.domain.models.Book

data class BookState(
    val isLoading: Boolean = false,
    val book: Book? = null,
    val error: String = ""
)
