package com.ailenaguino.booktracker.feature_add_book.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ailenaguino.booktracker.feature_add_book.data.database.entities.BookEntity

@Database(entities = [BookEntity::class], version = 1)
abstract class BookDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
}