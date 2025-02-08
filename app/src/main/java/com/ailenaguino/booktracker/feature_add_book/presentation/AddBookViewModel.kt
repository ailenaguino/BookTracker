package com.ailenaguino.booktracker.feature_add_book.presentation

import android.net.Uri
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.ailenaguino.booktracker.Screen
import com.ailenaguino.booktracker.feature_add_book.domain.models.Book
import com.ailenaguino.booktracker.feature_add_book.domain.usecases.SaveBookUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddBookViewModel @Inject constructor(
    private val saveBookUseCase: SaveBookUseCase
) : ViewModel() {
    private val _title = mutableStateOf("")
    val title = _title

    private val _author = mutableStateOf("")
    val author = _author

    private val _cover = mutableStateOf<Uri?>(null)
    val cover = _cover

    private val _totalPages = mutableStateOf("")
    val totalPages = _totalPages

    private val _typeBook = mutableStateOf("libro de papel")
    val typeBook = _typeBook

    private val _registerProgress = mutableStateOf("página")
    val registerProgress = _registerProgress

    private val _progress = mutableStateOf("leer más tarde")
    val progress = _progress

    private val _errorMessage = mutableStateOf("")
    var errorMessage = _errorMessage

    private val _result = mutableStateOf<Int?>(null)
    var result = _result

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

    fun onSaveBook(){
        if (title.value.isEmpty()) {
            errorMessage.value = "Por favor, ingrese un título"
        } else if (author.value.isEmpty()) {
            errorMessage.value = "Por favor, ingrese un autor"
        } else if (totalPages.value.isEmpty()) {
            errorMessage.value = "Por favor, ingrese un número de páginas"
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
                    errorMessage.value = "Error al guardar el libro"
                } else {
                    errorMessage.value = "Libro añadido con éxito"
                    _result.value = result.toInt()
                }
            }
            //errorMessage.value = ""
        }
    }
}