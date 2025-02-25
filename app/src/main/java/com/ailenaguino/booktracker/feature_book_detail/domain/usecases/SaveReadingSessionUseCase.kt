package com.ailenaguino.booktracker.feature_book_detail.domain.usecases

import com.ailenaguino.booktracker.feature_book_detail.domain.ReadingSessionRepository
import javax.inject.Inject

class SaveReadingSessionUseCase @Inject constructor(private val repository: ReadingSessionRepository){
    suspend operator fun invoke(hours: Int, minutes: Int, pagesRead: Int, state: String, bookId: Int, date: String){
        val totalMinutes = hours * 60 + minutes
        repository.saveReadingSession(totalMinutes, pagesRead, state, bookId, date)
    }
}