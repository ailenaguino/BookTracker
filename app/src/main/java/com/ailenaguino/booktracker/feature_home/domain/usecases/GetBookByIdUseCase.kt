package com.ailenaguino.booktracker.feature_home.domain.usecases

import com.ailenaguino.booktracker.common.Resource
import com.ailenaguino.booktracker.feature_add_book.domain.models.Book
import com.ailenaguino.booktracker.feature_home.domain.GetBooksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetBookByIdUseCase @Inject constructor(private val getBooksRepository: GetBooksRepository) {
    operator fun invoke(id: Int): Flow<Resource<Book>> = flow {
        try{
            emit(Resource.Loading())
            val book = getBooksRepository.getBookById(id)
            if(book == null){
                emit(Resource.Error("No se pudo agregar el libro"))
            }else{
                emit(Resource.Success(book))
            }
        } catch (e: Exception){
            emit(Resource.Error(e.message ?: "Un error inesperado ocurrió"))
        }
    }
}