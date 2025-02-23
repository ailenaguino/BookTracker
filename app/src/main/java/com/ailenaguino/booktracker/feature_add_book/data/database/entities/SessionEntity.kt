package com.ailenaguino.booktracker.feature_add_book.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.ailenaguino.booktracker.feature_book_detail.domain.models.ReadingSession

@Entity(
    tableName = "session_table", foreignKeys = [ForeignKey(
        entity = BookEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("book_id"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class SessionEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "book_id") val bookId: Int,
    @ColumnInfo(name = "reading_time") val readingTime: Int,
    @ColumnInfo(name = "current_page") val currentPage: Int,
    @ColumnInfo(name = "date") val date: String,
)

fun SessionEntity.toReadingSession(): ReadingSession {
    return ReadingSession(
        id = id,
        bookId = bookId,
        readingTime = readingTime,
        currentPage = currentPage,
        date = date
    )
}