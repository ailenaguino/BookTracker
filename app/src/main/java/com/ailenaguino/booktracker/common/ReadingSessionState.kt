package com.ailenaguino.booktracker.common

import com.ailenaguino.booktracker.feature_book_detail.domain.models.ReadingSession

data class ReadingSessionState(
    val isLoading: Boolean = false,
    val readingSession: List<ReadingSession>? = null,
    val error: String = ""

)
