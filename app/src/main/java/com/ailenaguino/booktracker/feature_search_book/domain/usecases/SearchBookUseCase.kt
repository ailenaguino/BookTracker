package com.ailenaguino.booktracker.feature_search_book.domain.usecases

import com.ailenaguino.booktracker.common.Resource
import com.ailenaguino.booktracker.feature_search_book.domain.SearchBookProvider
import com.ailenaguino.booktracker.feature_search_book.domain.models.Book
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SearchBookUseCase @Inject constructor(private val searchBookProvider: SearchBookProvider){
    operator fun invoke(title:String): Flow<Resource<List<Book>>> = flow {
        try{
            emit(Resource.Loading())
            val books = searchBookProvider.getBooksByTitle(title)
            emit(Resource.Success(books))
        }catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "Unexpected error occurred"))
        }catch (e: IOException){
            emit(Resource.Error("Couldn't reach server, check internet connection"))
        }
    }
}