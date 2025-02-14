package com.ailenaguino.booktracker.feature_home.domain.usecases

import com.ailenaguino.booktracker.common.Resource
import com.ailenaguino.booktracker.feature_add_book.domain.models.Book
import com.ailenaguino.booktracker.feature_home.domain.GetBooksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetGaveUpBooksUseCase @Inject constructor(private val getBooksRepository: GetBooksRepository) {
    operator fun invoke(): Flow<Resource<List<Book>>> = flow {
        try {
            emit(Resource.Loading())
            val books = getBooksRepository.getGaveUpBooks()
            emit(Resource.Success(books))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Un error inesperado ocurrió"))
        }
    }
}