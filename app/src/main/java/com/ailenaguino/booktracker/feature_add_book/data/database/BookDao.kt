package com.ailenaguino.booktracker.feature_add_book.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ailenaguino.booktracker.feature_add_book.data.database.entities.BookEntity

@Dao
interface BookDao {

    @Insert
    fun insertBook(book: BookEntity)

    @Query("SELECT * FROM book_table")
    fun getAllBooks(): List<BookEntity>

}