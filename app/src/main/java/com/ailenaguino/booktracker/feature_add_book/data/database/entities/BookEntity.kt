package com.ailenaguino.booktracker.feature_add_book.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ailenaguino.booktracker.feature_add_book.domain.models.Book

@Entity(tableName = "book_table")
data class BookEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "author") val author: String,
    @ColumnInfo(name = "cover") val cover: String,
    @ColumnInfo(name = "total_pages") val totalPages: Int,
    @ColumnInfo(name = "type_book") val typeBook: String,
    @ColumnInfo(name = "register_progress") val registerProgress: String,
    @ColumnInfo(name = "state") val state: String
)

fun BookEntity.toBook(): Book {
    return Book(
        id = id,
        title = title,
        author = author,
        cover = cover,
        totalPages = totalPages,
        typeBook = typeBook,
        registerProgress = registerProgress,
        state = state
    )
}
