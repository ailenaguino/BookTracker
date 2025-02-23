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

    @Query("SELECT * FROM session_table WHERE book_id = :bookId")
    suspend fun getSessionsByBookId(bookId: Int): List<SessionEntity>

}