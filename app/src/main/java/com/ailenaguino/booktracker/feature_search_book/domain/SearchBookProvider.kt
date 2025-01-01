package com.ailenaguino.booktracker.feature_search_book.domain

import com.ailenaguino.booktracker.feature_search_book.domain.models.Book

interface SearchBookProvider {
    suspend fun getBooksByTitle(title: String): List<Book>
}