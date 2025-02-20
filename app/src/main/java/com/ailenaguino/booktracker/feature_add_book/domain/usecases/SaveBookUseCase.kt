package com.ailenaguino.booktracker.feature_add_book.domain.usecases

import com.ailenaguino.booktracker.common.Constants.FINISHED
import com.ailenaguino.booktracker.common.Constants.GIVE_UP
import com.ailenaguino.booktracker.common.Constants.READ_LATER
import com.ailenaguino.booktracker.common.Constants.READ_NOW
import com.ailenaguino.booktracker.feature_add_book.domain.AddBookRepository
import com.ailenaguino.booktracker.feature_add_book.domain.models.Book
import javax.inject.Inject

class SaveBookUseCase @Inject constructor(private val repository: AddBookRepository) {
    suspend operator fun invoke(book: Book): Long? {
        book.apply {
            if (cover == "null") cover =
                "https://static.wikia.nocookie.net/gijoe/images/b/bf/Default_book_cover.jpg/revision/latest?cb=20240508080922"
            when(state){
                "read later" -> state = READ_LATER
                "read now" -> state = READ_NOW
                "I've already finished it" -> state = FINISHED
                "I gave up on it" -> state = GIVE_UP
            }
        }
        return repository.addBook(book)
    }
}