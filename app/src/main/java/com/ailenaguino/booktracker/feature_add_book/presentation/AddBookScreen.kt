package com.ailenaguino.booktracker.feature_add_book.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ailenaguino.booktracker.Screen
import com.ailenaguino.booktracker.common.Constants.AUDIO_BOOK
import com.ailenaguino.booktracker.common.Constants.EBOOK
import com.ailenaguino.booktracker.common.Constants.EPISODE
import com.ailenaguino.booktracker.common.Constants.FINISHED
import com.ailenaguino.booktracker.common.Constants.GAVE_UP
import com.ailenaguino.booktracker.common.Constants.PAGE
import com.ailenaguino.booktracker.common.Constants.PAPER_BOOK
import com.ailenaguino.booktracker.common.Constants.PERCENTAGE
import com.ailenaguino.booktracker.common.Constants.READ_LATER
import com.ailenaguino.booktracker.common.Constants.READ_NOW
import com.ailenaguino.booktracker.feature_add_book.presentation.components.AddCover
import com.ailenaguino.booktracker.feature_add_book.presentation.components.InputItem
import com.ailenaguino.booktracker.ui.sharedComponents.SaveFloatingButton
import com.ailenaguino.booktracker.feature_add_book.presentation.components.TagItem
import com.ailenaguino.booktracker.ui.sharedComponents.ArrowBack
import com.ailenaguino.booktracker.ui.theme.BoneBackground
import com.ailenaguino.booktracker.ui.theme.Orange

@OptIn(ExperimentalLayoutApi::class, ExperimentalFoundationApi::class)
@Composable
fun AddBookScreen(navController: NavController, viewModel: AddBookViewModel = hiltViewModel()) {
    val listState = rememberLazyListState()
    val title by viewModel.title
    val author by viewModel.author
    val totalPages by viewModel.totalPages
    val typeBook by viewModel.typeBook
    val registerProgress by viewModel.registerProgress
    val progress by viewModel.progress
    val errorMessage by viewModel.errorMessage
    val result by viewModel.result

    if (errorMessage.isNotEmpty()){
        Dialog({viewModel.onErrorChange("")}) {
            Text(errorMessage)
        }
        if(result != null){
            navController.navigate(Screen.HomeScreen.route + "/$result")
        }
    }

    val showButton by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex > 0
        }
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(BoneBackground)
            .padding(horizontal = 15.dp),
        state = listState
    ) {
        stickyHeader {
            Row(
                modifier = Modifier
                    .fillMaxWidth().padding(vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ArrowBack(showButton) { navController.popBackStack() }
                Box(modifier = Modifier.offset(x = 15.dp)) {
                    SaveFloatingButton(viewModel::onSaveBook)
                }
            }
        }
        item {
            Text(
                "Add a book",
                color = Color.DarkGray,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                modifier = Modifier.padding(vertical = 16.dp)
            )
        }
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp),
                contentAlignment = Alignment.Center
            ) {
                AddCover()
            }
        }
        item {
                InputItem("Title", KeyboardType.Text, title, viewModel::onTitleChange)
        }
        item {
            InputItem("Author", KeyboardType.Text, author, viewModel::onAuthorChange)
        }
        item {
            InputItem(
                "Total pages",
                KeyboardType.Number,
                totalPages,
                viewModel::onTotalPagesChange
            )
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "What type of book is it?",
                style = MaterialTheme.typography.headlineSmall,
                color = Orange,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = 16.dp).fillMaxWidth()
            )
            FlowRow(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                val typeBookOptions = listOf(PAPER_BOOK, EBOOK, AUDIO_BOOK)
                typeBookOptions.forEach { text ->
                    TagItem(text, text == typeBook, viewModel::onTypeBookChange)
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "How do you like to register your progress?",
                style = MaterialTheme.typography.headlineSmall,
                color = Orange,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = 16.dp).fillMaxWidth()
            )
            FlowRow(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                val registerProgressOptions = listOf(PAGE, PERCENTAGE, EPISODE)
                registerProgressOptions.forEach { text ->
                    TagItem(text, text == registerProgress, viewModel::onRegisterProgressChange)
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "State",
                style = MaterialTheme.typography.headlineSmall,
                color = Orange,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = 16.dp).fillMaxWidth()
            )
            FlowRow(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                val progressOptions =
                    listOf(READ_LATER, READ_NOW, FINISHED, GAVE_UP)
                progressOptions.forEach { text ->
                    TagItem(text, text == progress, viewModel::onProgressChange)
                }
            }
            Spacer(modifier = Modifier.height(18.dp))
        }
    }
}