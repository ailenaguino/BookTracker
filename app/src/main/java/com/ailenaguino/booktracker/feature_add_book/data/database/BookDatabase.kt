package com.ailenaguino.booktracker.feature_add_book.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ailenaguino.booktracker.feature_add_book.data.database.entities.BookEntity
import com.ailenaguino.booktracker.feature_add_book.data.database.entities.SessionEntity

@Database(entities = [BookEntity::class, SessionEntity::class], version = 5)
abstract class BookDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
}