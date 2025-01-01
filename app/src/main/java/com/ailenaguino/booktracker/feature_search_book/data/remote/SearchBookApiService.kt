package com.ailenaguino.booktracker.feature_search_book.data.remote

import com.ailenaguino.booktracker.common.Constants.API_KEY
import com.ailenaguino.booktracker.feature_search_book.data.dto.BookDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SearchBookApiService {

    @GET("/volumes")
    suspend fun getBooks(@Query("q") title: String, @Query("key") apiKey: String = API_KEY): List<BookDto>
}