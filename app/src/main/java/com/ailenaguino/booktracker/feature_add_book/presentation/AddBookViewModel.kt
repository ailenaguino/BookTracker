package com.ailenaguino.booktracker.feature_add_book.presentation

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AddBookViewModel @Inject constructor():ViewModel(){
    private val _title = MutableStateFlow("")
    val title = _title.asStateFlow()

    private val _author = MutableStateFlow("")
    val author = _author.asStateFlow()

    private val _cover = mutableStateOf<Uri?>(null)
    val cover = _cover

    private val _totalPages = MutableStateFlow("")
    val totalPages = _totalPages.asStateFlow()

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
}