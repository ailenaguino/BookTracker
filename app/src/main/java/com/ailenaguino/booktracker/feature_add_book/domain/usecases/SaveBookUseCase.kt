package com.ailenaguino.booktracker.feature_add_book.domain.usecases

import com.ailenaguino.booktracker.feature_add_book.domain.AddBookRepository
import com.ailenaguino.booktracker.feature_add_book.domain.models.Book
import javax.inject.Inject

class SaveBookUseCase @Inject constructor(private val repository: AddBookRepository) {
    suspend operator fun invoke(book: Book) {
        book.apply { if(cover == "null") cover = "" }
        repository.addBook(book)
    }
}