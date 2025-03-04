package com.ailenaguino.booktracker.feature_stopped.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ailenaguino.booktracker.common.ListBooksState
import com.ailenaguino.booktracker.common.Resource
import com.ailenaguino.booktracker.feature_add_book.domain.models.Book
import com.ailenaguino.booktracker.feature_home.domain.usecases.GetStoppedBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class StoppedViewModel @Inject constructor(private val getStoppedBooksUseCase: GetStoppedBooksUseCase) :
    ViewModel() {
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _books = MutableStateFlow(ListBooksState())
    val books: StateFlow<ListBooksState> = _books

    private val _filteredBooks = MutableStateFlow<List<Book>>(emptyList())
    val filteredBooks: StateFlow<List<Book>> = _filteredBooks

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    init {
        getBooks()
    }

    private fun getBooks() {
        getStoppedBooksUseCase().onEach {
            when (it) {
                is Resource.Error -> _books.value =
                    ListBooksState(error = it.message ?: "An unexpected error occurred")

                is Resource.Loading -> _books.value = ListBooksState(isLoading = true)
                is Resource.Success -> {
                    _books.value = ListBooksState(books = it.data ?: emptyList())
                    _filteredBooks.value = _books.value.books
                }
            }
        }.launchIn(viewModelScope)
    }

    fun searchBook(title: String) {
        _filteredBooks.value = _books.value.books.filter {
            (it.title.lowercase().contains(title.lowercase())
                    || it.author.lowercase().contains(title.lowercase()))
        }
    }
}