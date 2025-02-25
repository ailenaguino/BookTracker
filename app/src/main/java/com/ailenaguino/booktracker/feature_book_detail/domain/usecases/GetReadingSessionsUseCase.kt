package com.ailenaguino.booktracker.feature_book_detail.domain.usecases

import com.ailenaguino.booktracker.common.Resource
import com.ailenaguino.booktracker.feature_book_detail.domain.ReadingSessionRepository
import com.ailenaguino.booktracker.feature_book_detail.domain.models.ReadingSession
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetReadingSessionsUseCase @Inject constructor(private val repository: ReadingSessionRepository) {
    operator fun invoke(bookId: Int): Flow<Resource<List<ReadingSession>>> = flow {
        emit(Resource.Loading())
        try {
            val readingSessions = repository.getReadingSessions(bookId)
            emit(Resource.Success(readingSessions))
        } catch(e: Exception) {
            emit(Resource.Error(e.message ?: "An unexpected error occurred"))
        }
    }
}