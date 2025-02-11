package com.ailenaguino.booktracker.feature_library.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ailenaguino.booktracker.common.ListBooksState
import com.ailenaguino.booktracker.common.Resource
import com.ailenaguino.booktracker.feature_home.domain.usecases.GetBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LibraryViewModel @Inject constructor(private val getBooksUseCase: GetBooksUseCase): ViewModel() {
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _books = MutableStateFlow(ListBooksState())
    val books: StateFlow<ListBooksState> = _books

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    init{
        getBooks()
    }

    private fun getBooks() {
        getBooksUseCase().onEach {
            when (it) {
                is Resource.Error -> _books.value =
                    ListBooksState(error = it.message ?: "Un error inesperado ocurrió")
                is Resource.Loading -> _books.value = ListBooksState(isLoading = true)
                is Resource.Success -> _books.value = ListBooksState(books = it.data ?: emptyList())
            }
        }.launchIn(viewModelScope)
    }
}