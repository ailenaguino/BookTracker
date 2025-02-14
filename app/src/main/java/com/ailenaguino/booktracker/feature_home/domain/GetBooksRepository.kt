package com.ailenaguino.booktracker.feature_home.domain

import com.ailenaguino.booktracker.feature_add_book.domain.models.Book

interface GetBooksRepository {
    suspend fun getBooks(): List<Book>
    suspend fun getBookById(id: Int): Book?
    suspend fun deleteAllBooks(): Int
    suspend fun getReadLaterBooks(): List<Book>
    suspend fun getGaveUpBooks(): List<Book>
}