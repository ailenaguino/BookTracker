package com.ailenaguino.booktracker.feature_book_detail.domain

import com.ailenaguino.booktracker.feature_book_detail.domain.models.ReadingSession

interface ReadingSessionRepository {
    suspend fun saveReadingSession(
        minutes: Int,
        pagesRead: Int,
        state: String,
        bookId: Int,
        date: String,
        isFirst: Boolean,
    ): Long?

    suspend fun getReadingSessions(bookId: Int): List<ReadingSession>
}