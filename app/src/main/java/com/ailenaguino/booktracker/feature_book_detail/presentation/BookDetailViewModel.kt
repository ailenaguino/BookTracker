package com.ailenaguino.booktracker.feature_book_detail.presentation

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ailenaguino.booktracker.common.BookState
import com.ailenaguino.booktracker.common.Constants
import com.ailenaguino.booktracker.common.ReadingSessionState
import com.ailenaguino.booktracker.common.Resource
import com.ailenaguino.booktracker.feature_book_detail.domain.usecases.GetReadingSessionsUseCase
import com.ailenaguino.booktracker.feature_book_detail.domain.usecases.SaveReadingSessionUseCase
import com.ailenaguino.booktracker.feature_home.domain.usecases.GetBookByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getBookByIdUseCase: GetBookByIdUseCase,
    private val saveReadingSessionUseCase: SaveReadingSessionUseCase,
    private val getReadingSessionsUseCase: GetReadingSessionsUseCase,
) : ViewModel() {
    private val _book = MutableStateFlow(BookState())
    val book: StateFlow<BookState> = _book

    private val _readingSessions = MutableStateFlow(ReadingSessionState())
    val readingSessions: StateFlow<ReadingSessionState> = _readingSessions

    private val _hours = mutableStateOf("")
    val hours = _hours

    private val _minutes = mutableStateOf("")
    val minutes = _minutes

    private val _pagesRead = mutableStateOf("")
    val pagesRead = _pagesRead

    private val _state = mutableStateOf("")
    val state = _state

    private val _date = mutableStateOf("")
    val date = _date

    val hoursSaved = mutableIntStateOf(0)
    val minutesSaved = mutableIntStateOf(0)
    val pagesReadSaved = mutableIntStateOf(0)
    val lastPageSaved = mutableIntStateOf(0)
    val lastStateSaved = mutableStateOf("")
    val isSaved = mutableIntStateOf(0)
    val isFirstSession = mutableStateOf(false)

    private var bookId: Int = 0

    init {
        savedStateHandle.get<String>(Constants.PARAM_BOOK_ID)
            ?.let { bookId ->
                getBook(bookId.toInt())
                this.bookId = bookId.toInt()
                getReadingSessions(bookId.toInt())
            }
    }

    private fun getBook(id: Int) {
        getBookByIdUseCase(id).onEach {
            when (it) {
                is Resource.Error -> _book.value =
                    BookState(error = it.message ?: "An unexpected error occurred")

                is Resource.Loading -> _book.value = BookState(isLoading = true)
                is Resource.Success -> {
                    _book.value = BookState(book = it.data)
                    lastStateSaved.value = book.value.book!!.state
                }
            }
        }.launchIn(viewModelScope)
    }

    fun saveReadingSession() {
        viewModelScope.launch {
            _book.value.book.let {
                isSaved.intValue = saveReadingSessionUseCase(
                    hoursSaved.intValue,
                    minutesSaved.intValue,
                    pagesReadSaved.intValue,
                    _state.value.ifBlank { lastStateSaved.value },
                    bookId,
                    _date.value,
                    isFirstSession.value
                )?.toInt() ?: 0
            }
        }
    }

    private fun getReadingSessions(bookId: Int) {
        getReadingSessionsUseCase(bookId).onEach {
            when (it) {
                is Resource.Error -> {
                    _readingSessions.value =
                        ReadingSessionState(error = it.message ?: "An unexpected error occurred")
                    if (it.message == "No reading sessions found") {
                        isFirstSession.value = true
                    }
                }

                is Resource.Loading -> _readingSessions.value =
                    ReadingSessionState(isLoading = true)

                is Resource.Success -> {
                    _readingSessions.value =
                        ReadingSessionState(readingSession = it.data)
                    lastPageSaved.intValue =
                        readingSessions.value.readingSession!!.last().currentPage
                    isFirstSession.value = false
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onHoursChange(value: String) {
        _hours.value = value
    }

    fun onMinutesChange(value: String) {
        _minutes.value = value
    }

    fun onPagesReadChange(value: String) {
        _pagesRead.value = value
    }

    fun onStateChange(value: String) {
        _state.value = value
    }

    fun onDateChange(value: String) {
        _date.value = value
    }
}