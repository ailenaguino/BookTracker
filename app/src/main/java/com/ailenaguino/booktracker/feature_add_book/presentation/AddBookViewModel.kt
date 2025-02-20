package com.ailenaguino.booktracker.feature_add_book.presentation

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ailenaguino.booktracker.common.Constants
import com.ailenaguino.booktracker.common.Constants.PAGE
import com.ailenaguino.booktracker.common.Constants.PAPER_BOOK
import com.ailenaguino.booktracker.common.Constants.READ_LATER
import com.ailenaguino.booktracker.feature_add_book.domain.models.Book
import com.ailenaguino.booktracker.feature_add_book.domain.usecases.SaveBookUseCase
import com.ailenaguino.booktracker.feature_search_book.domain.models.GoogleBook
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddBookViewModel @Inject constructor(
    private val saveBookUseCase: SaveBookUseCase, savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _title = mutableStateOf("")
    val title = _title

    private val _author = mutableStateOf("")
    val author = _author

    private val _cover = mutableStateOf<Uri?>(null)
    val cover = _cover

    private val _totalPages = mutableStateOf("")
    val totalPages = _totalPages

    private val _typeBook = mutableStateOf(PAPER_BOOK)
    val typeBook = _typeBook

    private val _registerProgress = mutableStateOf(PAGE)
    val registerProgress = _registerProgress

    private val _progress = mutableStateOf(READ_LATER)
    val progress = _progress

    private val _errorMessage = mutableStateOf("")
    var errorMessage = _errorMessage

    private val _result = mutableStateOf<Int?>(null)
    var result = _result

    init {
        savedStateHandle.get<String>(Constants.PARAM_BOOK)
            ?.let { book -> Log.i("param book", book)
            val decodedBook = Gson().fromJson(book, GoogleBook::class.java)
            if (decodedBook!=null) dataFromGoogleBooks(decodedBook)}
    }

    fun onErrorChange(text: String) {
        _errorMessage.value = text
    }

    fun onTitleChange(text: String) {
        _title.value = text
    }

    fun onAuthorChange(text: String) {
        _author.value = text
    }

    fun onTotalPagesChange(text: String) {
        _totalPages.value = text
    }

    fun onCoverChange(uri: Uri) {
        _cover.value = uri
    }

    fun onTypeBookChange(text: String) {
        _typeBook.value = text
    }

    fun onRegisterProgressChange(text: String) {
        _registerProgress.value = text
    }

    fun onProgressChange(text: String) {
        _progress.value = text
    }

    fun onSaveBook() {
        if (title.value.isEmpty()) {
            errorMessage.value = "Please, write a title"
        } else if (author.value.isEmpty()) {
            errorMessage.value = "Please, write an author"
        } else if (totalPages.value.isEmpty()) {
            errorMessage.value = "Please, write the total pages"
        } else {
            val book = Book(
                null,
                title.value,
                author.value,
                cover.value.toString(),
                totalPages.value.toInt(),
                typeBook.value,
                registerProgress.value,
                progress.value
            )
            viewModelScope.launch {
                val result = saveBookUseCase(book)
                if (result == null) {
                    errorMessage.value = "An error occurred while adding the book"
                } else {
                    errorMessage.value = "Book added successfully"
                    _result.value = result.toInt()
                }
            }
            //errorMessage.value = ""
        }
    }

    private fun dataFromGoogleBooks(book: GoogleBook) {
        _title.value = book.title
        _author.value = book.author.joinToString(", ")
        _cover.value = Uri.parse(book.cover)
        _totalPages.value = book.pageCount.toString()
    }
}