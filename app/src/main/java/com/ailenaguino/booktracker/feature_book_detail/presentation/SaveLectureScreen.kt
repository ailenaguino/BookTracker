package com.ailenaguino.booktracker.feature_book_detail.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.MenuBook
import androidx.compose.material.icons.rounded.Bookmark
import androidx.compose.material.icons.rounded.Timer
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ailenaguino.booktracker.Screen
import com.ailenaguino.booktracker.feature_book_detail.presentation.components.PagesReadModal
import com.ailenaguino.booktracker.feature_book_detail.presentation.components.ReadingTimeModal
import com.ailenaguino.booktracker.feature_book_detail.presentation.components.SaveLectureCard
import com.ailenaguino.booktracker.feature_book_detail.presentation.components.StateModal
import com.ailenaguino.booktracker.ui.sharedComponents.ArrowBack
import com.ailenaguino.booktracker.ui.sharedComponents.SaveFloatingButton
import com.ailenaguino.booktracker.ui.theme.BoneBackground
import com.ailenaguino.booktracker.ui.theme.Orange
import java.util.Calendar

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SaveLectureScreen(
    viewModel: BookDetailViewModel = hiltViewModel(),
    navController: NavController,
) {
    val listState = rememberLazyListState()
    var showReadingTimeModal by remember { mutableStateOf(false) }
    var showPagesReadModal by remember { mutableStateOf(false) }
    var showStateModal by remember { mutableStateOf(false) }
    val isSaved by viewModel.isSaved
    val book by viewModel.book.collectAsState()
    val showButton by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex > 0
        }
    }

    AnimatedVisibility(
        showReadingTimeModal,
        enter = slideInVertically(),
        exit = slideOutVertically()
    ) {
        ReadingTimeModal { showReadingTimeModal = false }
    }

    AnimatedVisibility(
        showPagesReadModal,
        enter = slideInVertically(),
        exit = slideOutVertically()
    ) {
        PagesReadModal { showPagesReadModal = false }
    }

    AnimatedVisibility(showStateModal, enter = slideInVertically(), exit = slideOutVertically()) {
        StateModal { showStateModal = false }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BoneBackground)
    ) {
        if (book.isLoading) {
            Box(modifier = Modifier.fillMaxWidth()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = Orange,
                    strokeWidth = 5.dp,
                    trackColor = Color.White
                )
            }
        }
        if (book.error.isNotBlank()) {
            Text(
                text = book.error,
                color = Color.Red,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            )
        }
    }


    if (book.book != null) {
        if(isSaved != 0){
            navController.navigate(Screen.BookDetailScreen.route + "/${book.book!!.id}")
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(BoneBackground)
                .padding(horizontal = 15.dp)
        ) {
            stickyHeader {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    ArrowBack(showButton) { navController.popBackStack() }
                    Box(modifier = Modifier.offset(x = 15.dp)) {
                        SaveFloatingButton(viewModel::saveReadingSession)
                    }
                }
            }
            item {
                Text(
                    "Save reading session",
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
                Text(getCurrentDay(viewModel), color = Color.DarkGray)
                Spacer(modifier = Modifier.size(20.dp))
            }
            item {
                SaveLectureCard(
                    Icons.Rounded.Timer,
                    (if (viewModel.minutesSaved.intValue > 0) formatHours(viewModel.hoursSaved.intValue, viewModel.minutesSaved.intValue) else "00:00"),
                    "reading time",
                    ""
                ) {
                    showReadingTimeModal = true
                    showPagesReadModal = false
                    showStateModal = false
                }
            }
            item {
                SaveLectureCard(
                    Icons.Rounded.Bookmark,
                    if (viewModel.pagesReadSaved.intValue > 0) "Page ${viewModel.pagesReadSaved.intValue}" else "Page ${viewModel.lastPageSaved.intValue}",
                    "current page",
                    "/ " + book.book!!.totalPages
                ) {
                    showPagesReadModal = true
                    showReadingTimeModal = false
                    showStateModal = false
                }
            }
            item {
                SaveLectureCard(
                    Icons.AutoMirrored.Rounded.MenuBook,
                    viewModel.state.value.ifBlank { viewModel.lastStateSaved.value },
                    "state",
                    ""
                ) {
                    showStateModal = true
                    showPagesReadModal = false
                    showReadingTimeModal = false
                }

            }

        }
    }
}

fun getCurrentDay(viewModel: BookDetailViewModel): String {
    val calendar = Calendar.getInstance()
    val minute = if (calendar.get(Calendar.MINUTE) < 10) {
        "0" + calendar.get(Calendar.MINUTE)
    } else {
        calendar.get(Calendar.MINUTE)
    }
    val date = "${calendar.get(Calendar.DAY_OF_MONTH)}/" +
            "${calendar.get(Calendar.MONTH)}/" +
            "${calendar.get(Calendar.YEAR)} | " +
            "${calendar.get(Calendar.HOUR_OF_DAY)}:" +
            "$minute"
    viewModel.onDateChange(date)
    return date
}

fun formatHours(hours: Int, minutes: Int): String{
    var formattedHours = ""
    if(hours.toString().length == 1) formattedHours = "0$hours:" else formattedHours = "$hours:"
    if(minutes.toString().length == 1) formattedHours += "0$minutes" else formattedHours += "$minutes"
    return formattedHours
}