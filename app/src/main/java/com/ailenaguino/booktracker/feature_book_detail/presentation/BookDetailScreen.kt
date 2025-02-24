package com.ailenaguino.booktracker.feature_book_detail.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.NoteAdd
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ailenaguino.booktracker.Screen
import com.ailenaguino.booktracker.feature_book_detail.presentation.components.CoverItem
import com.ailenaguino.booktracker.feature_book_detail.presentation.components.CurrentPageItem
import com.ailenaguino.booktracker.ui.sharedComponents.ArrowBack
import com.ailenaguino.booktracker.ui.theme.Orange
import com.ailenaguino.booktracker.ui.theme.BoneBackground
import com.ailenaguino.booktracker.ui.theme.Grey
import com.ailenaguino.booktracker.ui.theme.LightBlue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BookDetailScreen(
    navController: NavController,
    viewModel: BookDetailViewModel = hiltViewModel(),
) {
    val listState = rememberLazyListState()
    val book by viewModel.book.collectAsState()
    val showButton by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex > 0
        }
    }
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
    if (book.book != null) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(BoneBackground)
                .padding(horizontal = 15.dp),
            state = listState
        ) {
            stickyHeader {
                ArrowBack(showButton) { navController.popBackStack() }
                Spacer(modifier = Modifier.padding(top = 20.dp))
            }
            item {
                CoverItem(
                    book.book!!.cover,
                    book.book!!.title,
                    book.book!!.author,
                    "4",
                    "13/02/2025"
                )
                Spacer(modifier = Modifier.padding(top = 40.dp))
            }
            item {
                CurrentPageItem(book.book!!.totalPages.toFloat(), 100f) {navController.navigate(Screen.SaveLectureScreen.route + "/${book.book!!.id}")}
            }
            item {
                Spacer(modifier = Modifier.padding(top = 40.dp))
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(30.dp))
                        .background(LightBlue)
                        .padding(30.dp)
                ) {
                    Text(
                        "Information about the book",
                        color = Grey,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        "State: " + book.book!!.state,
                        color = Grey,
                        style = MaterialTheme.typography.titleSmall
                    )
                    Text(
                        "You want to register the progress by: " + book.book!!.registerProgress,
                        color = Grey,
                        style = MaterialTheme.typography.titleSmall
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            FloatingActionButton(
                {},
                containerColor = Orange,
                contentColor = Color.White,
                shape = CircleShape
            ) {
                Icon(Icons.AutoMirrored.Rounded.NoteAdd, "Add note")
            }
        }
    }
}
