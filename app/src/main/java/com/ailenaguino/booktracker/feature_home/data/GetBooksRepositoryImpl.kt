package com.ailenaguino.booktracker.feature_home.data

import com.ailenaguino.booktracker.feature_add_book.data.database.BookDao
import com.ailenaguino.booktracker.feature_add_book.data.database.entities.toBook
import com.ailenaguino.booktracker.feature_add_book.domain.models.Book
import com.ailenaguino.booktracker.feature_home.domain.GetBooksRepository
import javax.inject.Inject

class GetBooksRepositoryImpl @Inject constructor(private val bookDao: BookDao): GetBooksRepository {
    override suspend fun getBooks(): List<Book> {
        val books = bookDao.getAllBooks()
        return if (books.isNotEmpty()) {
            books.map { it.toBook() }
        } else {
            emptyList()
        }
    }

    override suspend fun getBookById(id: Int): Book? {
        val book = bookDao.getBookById(id)
        return book.let { it?.toBook() }

    }

    override suspend fun deleteAllBooks(): Int {
        return bookDao.deleteAllBooks()
    }

    override suspend fun getReadLaterBooks(): List<Book> {
        return bookDao.getReadLaterBooks().map { it.toBook() }
    }
}