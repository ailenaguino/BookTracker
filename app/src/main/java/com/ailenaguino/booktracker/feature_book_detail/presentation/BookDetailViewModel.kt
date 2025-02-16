package com.ailenaguino.booktracker.feature_book_detail.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ailenaguino.booktracker.common.BookState
import com.ailenaguino.booktracker.common.Constants
import com.ailenaguino.booktracker.common.Resource
import com.ailenaguino.booktracker.feature_home.domain.usecases.GetBookByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class BookDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getBookByIdUseCase: GetBookByIdUseCase
) : ViewModel() {
    private val _book = MutableStateFlow(BookState())
    val book: StateFlow<BookState> = _book

    init {
        savedStateHandle.get<String>(Constants.PARAM_BOOK_ID)
            ?.let { bookId -> getBook(bookId.toInt()) }
    }

    private fun getBook(id: Int) {
        getBookByIdUseCase(id).onEach {
            when(it){
                is Resource.Error -> _book.value =
                    BookState(error = it.message ?: "Un error inesperado ocurrió")
                is Resource.Loading -> _book.value = BookState(isLoading = true)
                is Resource.Success -> _book.value = BookState(book = it.data)
            }
        }.launchIn(viewModelScope)
    }
}