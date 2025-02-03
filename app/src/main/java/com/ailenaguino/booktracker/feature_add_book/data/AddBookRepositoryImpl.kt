package com.ailenaguino.booktracker.feature_add_book.data

import com.ailenaguino.booktracker.feature_add_book.data.database.BookDao
import com.ailenaguino.booktracker.feature_add_book.data.database.entities.BookEntity
import com.ailenaguino.booktracker.feature_add_book.domain.AddBookRepository
import com.ailenaguino.booktracker.feature_add_book.domain.models.Book
import javax.inject.Inject

class AddBookRepositoryImpl @Inject constructor(private val bookDao: BookDao) : AddBookRepository {
    override suspend fun addBook(book: Book) {
        val bookEntity = BookEntity(
            title = book.title,
            author = book.author,
            cover = book.cover,
            totalPages = book.totalPages,
            typeBook = book.typeBook,
            registerProgress = book.registerProgress,
            progress = book.progress
        )
        bookDao.insertBook(bookEntity)
    }
}