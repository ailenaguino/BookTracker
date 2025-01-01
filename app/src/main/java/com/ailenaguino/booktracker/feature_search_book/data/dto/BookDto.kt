package com.ailenaguino.booktracker.feature_search_book.data.dto

import com.ailenaguino.booktracker.feature_search_book.domain.models.Book
import com.google.gson.annotations.SerializedName

data class BookDto(
    @SerializedName("title") val title: String,
    @SerializedName("authors") val author: List<String>,
    @SerializedName("pageCount") val pageCount: Int,
    @SerializedName("imageLinks") val imageLinks: List<ImageLinks>,
)

data class ImageLinks(@SerializedName("smallThumbnail") val smallThumbnail: String)

data class Items(@SerializedName("items") val items: List<BookDto>)

fun BookDto.toBook(): Book {
    return Book(
        title = title,
        author = author,
        cover = imageLinks[0].smallThumbnail,
        pageCount = pageCount
    )
}