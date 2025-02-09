package com.ailenaguino.booktracker.feature_search_book.data.dto

import com.ailenaguino.booktracker.feature_search_book.domain.models.GoogleBook
import com.google.gson.annotations.SerializedName

data class BookDto(
    @SerializedName("title") val title: String,
    @SerializedName("authors") val author: List<String>?,
    @SerializedName("pageCount") val pageCount: Int,
    @SerializedName("imageLinks") val imageLinks: ImageLinks?,
)

data class ImageLinks(@SerializedName("smallThumbnail") val smallThumbnail: String)

data class Volumes(@SerializedName("volumeInfo") val volumeInfo: BookDto)
data class Items(@SerializedName("items") val items: List<Volumes>)

fun BookDto.toBook(): GoogleBook {
    return GoogleBook(
        title = title,
        author = author ?: listOf("Desconocido"),
        cover = imageLinks?.smallThumbnail ?: "https://static.wikia.nocookie.net/gijoe/images/b/bf/Default_book_cover.jpg/revision/latest?cb=20240508080922",
        pageCount = pageCount
    )
}