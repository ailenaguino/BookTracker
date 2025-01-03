package com.ailenaguino.booktracker.feature_search_book.data

import com.ailenaguino.booktracker.feature_search_book.data.dto.toBook
import com.ailenaguino.booktracker.feature_search_book.data.remote.SearchBookApiService
import com.ailenaguino.booktracker.feature_search_book.domain.SearchBookProvider
import com.ailenaguino.booktracker.feature_search_book.domain.models.Book
import javax.inject.Inject

class SearchBookProviderImpl @Inject constructor(private val apiService: SearchBookApiService) :
    SearchBookProvider {
    override suspend fun getBooksByTitle(title: String): List<Book> {
        val volumes = apiService.getBooks("intitle:${title}").items
        return volumes.map { it.volumeInfo.toBook() }
    }
}