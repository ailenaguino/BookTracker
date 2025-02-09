package com.ailenaguino.booktracker.feature_search_book.data

import com.ailenaguino.booktracker.feature_search_book.data.dto.toBook
import com.ailenaguino.booktracker.feature_search_book.data.remote.SearchBookApiService
import com.ailenaguino.booktracker.feature_search_book.domain.SearchBookProvider
import com.ailenaguino.booktracker.feature_search_book.domain.models.GoogleBook
import javax.inject.Inject

class SearchBookProviderImpl @Inject constructor(private val apiService: SearchBookApiService) :
    SearchBookProvider {
    override suspend fun getBooksByTitle(title: String): List<GoogleBook> {
        val volumes = apiService.getBooks("intitle:${title}").items
        return if (!volumes.isNullOrEmpty()) {
            volumes.map { it.volumeInfo.toBook() }
        }else emptyList()
    }
}