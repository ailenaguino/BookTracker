package com.ailenaguino.booktracker.feature_add_book.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ailenaguino.booktracker.common.Constants
import com.ailenaguino.booktracker.feature_add_book.data.database.entities.BookEntity
import com.ailenaguino.booktracker.feature_add_book.data.database.entities.SessionEntity

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book: BookEntity): Long?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSession(session: SessionEntity): Long?

    @Query("SELECT * FROM book_table")
    suspend fun getAllBooks(): List<BookEntity>

    @Query("SELECT * FROM book_table WHERE id = :id")
    suspend fun getBookById(id: Int): BookEntity?

    @Query("DELETE FROM book_table")
    suspend fun deleteAllBooks(): Int

    @Query("SELECT * FROM book_table WHERE state = '${Constants.READ_LATER}'")
    suspend fun getReadLaterBooks(): List<BookEntity>

    @Query("SELECT * FROM book_table WHERE state = '${Constants.GAVE_UP}'")
    suspend fun getGaveUpBooks(): List<BookEntity>

    @Query("SELECT * FROM book_table WHERE state = '${Constants.READ_NOW}'")
    suspend fun getReadNowBooks(): List<BookEntity>

    @Query("SELECT * FROM book_table WHERE state = '${Constants.PAUSED}'")
    suspend fun getStoppedBooks(): List<BookEntity>

    @Query("SELECT * FROM session_table WHERE book_id = :bookId")
    suspend fun getSessionsByBookId(bookId: Int): List<SessionEntity>

    @Query("UPDATE book_table SET state = :state WHERE id = :bookId")
    suspend fun updateBookState(bookId: Int, state: String)

    @Query("UPDATE book_table SET current_page = :currentPage WHERE id = :bookId")
    suspend fun updateCurrentPage(bookId: Int, currentPage: Int)

    @Query("UPDATE book_table SET started_day = :startedDay WHERE id = :bookId")
    suspend fun updateStartedDay(bookId: Int, startedDay: String)

    @Query("UPDATE book_table SET sessions = :sessions WHERE id = :bookId")
    suspend fun updateSessions(bookId: Int, sessions: Int)

}