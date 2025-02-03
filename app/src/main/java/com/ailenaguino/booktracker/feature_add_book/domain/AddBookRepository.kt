package com.ailenaguino.booktracker.feature_add_book.domain

import com.ailenaguino.booktracker.feature_add_book.domain.models.Book

interface AddBookRepository {
    suspend fun addBook(book: Book)
}