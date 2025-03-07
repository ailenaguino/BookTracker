package com.ailenaguino.booktracker.feature_book_detail.data

import com.ailenaguino.booktracker.feature_add_book.data.database.BookDao
import com.ailenaguino.booktracker.feature_add_book.data.database.entities.SessionEntity
import com.ailenaguino.booktracker.feature_add_book.data.database.entities.toReadingSession
import com.ailenaguino.booktracker.feature_book_detail.domain.ReadingSessionRepository
import com.ailenaguino.booktracker.feature_book_detail.domain.models.ReadingSession
import javax.inject.Inject

class ReadingSessionRepositoryImpl @Inject constructor(private val bookDao: BookDao) :
    ReadingSessionRepository {
    override suspend fun saveReadingSession(
        minutes: Int,
        pagesRead: Int,
        state: String,
        bookId: Int,
        date: String,
        isFirst: Boolean
    ): Long? {
        val session = SessionEntity(
            bookId = bookId,
            readingTime = minutes,
            currentPage = pagesRead,
            date = date
        )
        bookDao.updateBookState(bookId, state)
        bookDao.updateCurrentPage(bookId, pagesRead)
        bookDao.addOneSession(bookId)
        if(isFirst){
            bookDao.addStartedDay(bookId, date)
        }
        return bookDao.insertSession(session)
    }

    override suspend fun getReadingSessions(bookId: Int): List<ReadingSession> {
        val sessions = bookDao.getSessionsByBookId(bookId)
        return if (sessions.isNotEmpty()) {
            sessions.map { it.toReadingSession() }
        } else {
            emptyList()
        }
    }
}