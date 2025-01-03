package com.ailenaguino.booktracker.feature_search_book.domain.models

data class Book(val title: String, val author: List<String>, var cover: String, val pageCount: Int)
