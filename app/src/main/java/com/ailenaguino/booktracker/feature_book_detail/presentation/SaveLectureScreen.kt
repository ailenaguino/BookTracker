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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ailenaguino.booktracker.feature_book_detail.presentation.components.PagesReadModal
import com.ailenaguino.booktracker.feature_book_detail.presentation.components.ReadingTimeModal
import com.ailenaguino.booktracker.feature_book_detail.presentation.components.SaveLectureCard
import com.ailenaguino.booktracker.ui.sharedComponents.ArrowBack
import com.ailenaguino.booktracker.ui.sharedComponents.SaveFloatingButton
import com.ailenaguino.booktracker.ui.theme.BoneBackground
import java.util.Calendar

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun SaveLectureScreen() {
    val listState = rememberLazyListState()
    var showReadingTimeModal by remember { mutableStateOf(false) }
    var showPagesReadModal by remember { mutableStateOf(false) }
    val showButton by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex > 0
        }
    }

    AnimatedVisibility(showReadingTimeModal, enter = slideInVertically(), exit = slideOutVertically()) {
        ReadingTimeModal { showReadingTimeModal = false }
    }

    AnimatedVisibility(showPagesReadModal, enter = slideInVertically(), exit = slideOutVertically()) {
        PagesReadModal { showPagesReadModal = false }
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
                ArrowBack(showButton) { /*navController.popBackStack()*/ }
                Box(modifier = Modifier.offset(x = 15.dp)) {
                    SaveFloatingButton({}/*viewModel::onSaveBook*/)
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
            Text(getCurrentDay(), color = Color.DarkGray)
            Spacer(modifier = Modifier.size(20.dp))
        }
        item {
            SaveLectureCard(Icons.Rounded.Timer, "00:00:00", "tiempo de lectura", "") {
                showReadingTimeModal = true
                showPagesReadModal = false
            }
        }
        item {
            SaveLectureCard(Icons.Rounded.Bookmark, "Page 12", "pages", "/ 100") {
                showPagesReadModal = true
                showReadingTimeModal = false
            }
        }
        item {
            SaveLectureCard(Icons.AutoMirrored.Rounded.MenuBook, "Read Now", "state", "", {})
        }

    }
}

fun getCurrentDay(): String {
    val calendar = Calendar.getInstance()
    val minute = if (calendar.get(Calendar.MINUTE) < 10) {
        "0" + calendar.get(Calendar.MINUTE)
    } else {
        calendar.get(Calendar.MINUTE)
    }
    return "${calendar.get(Calendar.DAY_OF_MONTH)}/" +
            "${calendar.get(Calendar.MONTH)}/" +
            "${calendar.get(Calendar.YEAR)} | " +
            "${calendar.get(Calendar.HOUR_OF_DAY)}:" +
            "$minute"
}