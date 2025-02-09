package com.ailenaguino.booktracker.feature_search_book.domain

import com.ailenaguino.booktracker.feature_search_book.domain.models.GoogleBook

interface SearchBookProvider {
    suspend fun getBooksByTitle(title: String): List<GoogleBook>
}