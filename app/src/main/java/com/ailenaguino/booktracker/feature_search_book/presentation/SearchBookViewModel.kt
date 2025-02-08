package com.ailenaguino.booktracker.feature_search_book.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ailenaguino.booktracker.common.Resource
import com.ailenaguino.booktracker.feature_search_book.domain.usecases.SearchBookUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SearchBookViewModel @Inject constructor(private val useCase: SearchBookUseCase):ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _books = mutableStateOf(GoogleBooksState())
    val books: State<GoogleBooksState> = _books

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    fun getBooks() {
        useCase(_searchText.value).onEach { result ->
            when(result){
                is Resource.Error -> _books.value = GoogleBooksState(error = result.message ?: "An unexpected error ocurred")
                is Resource.Loading -> _books.value = GoogleBooksState(isLoading = true)
                is Resource.Success -> _books.value = GoogleBooksState(books = result.data ?: emptyList())
            }
        }.launchIn(viewModelScope)
    }
}