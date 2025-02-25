package com.ailenaguino.booktracker.feature_home.presentation

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ailenaguino.booktracker.common.BookState
import com.ailenaguino.booktracker.common.Constants
import com.ailenaguino.booktracker.common.Constants.GAVE_UP
import com.ailenaguino.booktracker.common.Constants.READ_LATER
import com.ailenaguino.booktracker.common.ListBooksState
import com.ailenaguino.booktracker.common.Resource
import com.ailenaguino.booktracker.feature_add_book.domain.models.Book
import com.ailenaguino.booktracker.feature_home.domain.GetBooksRepository
import com.ailenaguino.booktracker.feature_home.domain.usecases.GetBookByIdUseCase
import com.ailenaguino.booktracker.feature_home.domain.usecases.GetBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getBooksUseCase: GetBooksUseCase,
    savedStateHandle: SavedStateHandle,
    private val getBookByIdUseCase: GetBookByIdUseCase,
    private val repo: GetBooksRepository
) : ViewModel() {

    private val _books = MutableStateFlow(ListBooksState())
    val books: StateFlow<ListBooksState> = _books

    private val _book = MutableStateFlow(BookState())
    val book: StateFlow<BookState> = _book

    private val _openBookAddedDialog = MutableStateFlow(false)
    val openBookAddedDialog: StateFlow<Boolean> = _openBookAddedDialog

    private val _wasNotified = MutableStateFlow(false)
    val wasNotified: StateFlow<Boolean> = _wasNotified

    init {
        getBooks()
        savedStateHandle.get<String>(Constants.PARAM_BOOK_ID)
            ?.let { bookId -> getBookAdded(bookId.toInt()) }
    }

    private fun getBooks() {
        getBooksUseCase().onEach {
            when (it) {
                is Resource.Error -> _books.value =
                    ListBooksState(error = it.message ?: "An unexpected error occurred")

                is Resource.Loading -> _books.value = ListBooksState(isLoading = true)
                is Resource.Success -> _books.value = ListBooksState(books = it.data ?: emptyList())
            }
        }.launchIn(viewModelScope)
    }

    fun refreshReadLater() {
        getBooks()
    }

    private fun getBookAdded(id: Int) {
        getBookByIdUseCase(id).onEach {
            when(it){
                is Resource.Error -> _book.value =
                    BookState(error = it.message ?: "An unexpected error occurred")
                is Resource.Loading -> _book.value = BookState(isLoading = true)
                is Resource.Success -> _book.value = BookState(book = it.data)
            }
        }.launchIn(viewModelScope)
    }

    fun deleteAllBooks(){
        viewModelScope.launch {
            val result = repo.deleteAllBooks()
            Log.i("delete books", "$result")
        }
    }

    fun onWasNotifiedChange(value: Boolean){
        _wasNotified.value = value
    }

    fun onOpenBookDialogChange(value: Boolean){
        _openBookAddedDialog.value = value
    }

    fun getReadLaterBooks(): List<Book> {
        return _books.value.books.filter { it.state == READ_LATER }
    }

    fun getGaveUpBooks(): List<Book> {
        return _books.value.books.filter { it.state == GAVE_UP }
    }
}