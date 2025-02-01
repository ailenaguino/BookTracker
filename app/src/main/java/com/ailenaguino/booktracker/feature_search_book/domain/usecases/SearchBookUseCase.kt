package com.ailenaguino.booktracker.feature_search_book.domain.usecases

import com.ailenaguino.booktracker.common.Resource
import com.ailenaguino.booktracker.feature_search_book.domain.SearchBookProvider
import com.ailenaguino.booktracker.feature_search_book.domain.models.Book
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SearchBookUseCase @Inject constructor(
    private val searchBookProvider: SearchBookProvider,
    private val formatBookThumbnailUseCase: FormatBookThumbnailUseCase
) {
    operator fun invoke(title: String): Flow<Resource<List<Book>>> = flow {
        try {
            emit(Resource.Loading())
            val books = searchBookProvider.getBooksByTitle(title)
            if (books.isEmpty()) {
                emit(Resource.Error("No books found"))
            }else{
                val newList = books.distinctBy { Pair(it.title, it.author) }
                newList.forEach {
                    it.cover = formatBookThumbnailUseCase(it.cover)
                    it.cover = it.cover.replace("http:", "https:")
                }
                emit(Resource.Success(newList))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server, check internet connection"))
        }
    }
}