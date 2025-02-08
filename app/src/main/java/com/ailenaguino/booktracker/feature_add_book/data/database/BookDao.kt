package com.ailenaguino.booktracker.feature_add_book.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ailenaguino.booktracker.feature_add_book.data.database.entities.BookEntity

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book: BookEntity): Long?

    @Query("SELECT * FROM book_table")
    suspend fun getAllBooks(): List<BookEntity>

    @Query("SELECT * FROM book_table WHERE id = :id")
    suspend fun getBookById(id: Int): BookEntity?

}