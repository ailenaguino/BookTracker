package com.ailenaguino.booktracker.feature_add_book.domain.models

import android.net.Uri

data class Book(
    val id: Int?,
    val title: String,
    val author: String,
    var cover: String,
    val totalPages: Int,
    val typeBook: String,
    val registerProgress: String,
    val progress: String
)
