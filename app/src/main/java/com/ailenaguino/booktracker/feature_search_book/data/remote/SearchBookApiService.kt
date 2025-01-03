package com.ailenaguino.booktracker.feature_search_book.data.remote

import com.ailenaguino.booktracker.common.Constants.API_KEY
import com.ailenaguino.booktracker.feature_search_book.data.dto.BookDto
import com.ailenaguino.booktracker.feature_search_book.data.dto.Items
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SearchBookApiService {

    @GET("/books/v1/volumes")
    suspend fun getBooks(
        @Query("q") title: String,
        @Query(value = "key", encoded = true) apiKey: String = API_KEY,
        @Query("maxResults") maxResults: Int = 10
    ): Items

}