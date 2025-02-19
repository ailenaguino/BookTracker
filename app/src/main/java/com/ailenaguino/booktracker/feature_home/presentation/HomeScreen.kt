package com.ailenaguino.booktracker.feature_home.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.MenuBook
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Flag
import androidx.compose.material.icons.rounded.Pause
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ailenaguino.booktracker.Screen
import com.ailenaguino.booktracker.feature_home.presentation.components.AddBookItem
import com.ailenaguino.booktracker.feature_home.presentation.components.BookAddedDialog
import com.ailenaguino.booktracker.feature_home.presentation.components.CardItem
import com.ailenaguino.booktracker.feature_home.presentation.components.CollectionsTitle
import com.ailenaguino.booktracker.feature_home.presentation.components.NewReadLaterItem
import com.ailenaguino.booktracker.feature_home.presentation.components.ReadLaterItem
import com.ailenaguino.booktracker.ui.sharedComponents.AddBookDialog
import com.ailenaguino.booktracker.ui.theme.Orange
import com.ailenaguino.booktracker.ui.theme.BoneBackground
import com.ailenaguino.booktracker.ui.theme.BoneBackgroundTransp
import com.ailenaguino.booktracker.ui.theme.Grey
import com.ailenaguino.booktracker.ui.theme.ItemBackground

val modifierForCards = Modifier
    .shadow(10.dp, RoundedCornerShape(10.dp))
    .fillMaxWidth()
    .clip(RoundedCornerShape(10.dp))
    .background(ItemBackground)
    .padding(20.dp)

val modifierForCollections = Modifier
    .shadow(10.dp, RoundedCornerShape(10.dp))
    .clip(RoundedCornerShape(10.dp))
    .background(ItemBackground)
    .padding(15.dp, 15.dp, 15.dp, 15.dp)
    .height(200.dp)
    .width(IntrinsicSize.Max)

@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {
    var visible by remember { mutableStateOf(false) }
    var openBookAddedDialog by remember { mutableStateOf(false) }
    var wasNotified by remember { mutableStateOf(false) }
    val books by viewModel.books.collectAsState()
    val bookAdded by viewModel.book.collectAsState()

    AnimatedVisibility(visible, enter = slideInVertically(), exit = slideOutVertically()) {
        AddBookDialog(navController) { visible = false }
    }

    if (bookAdded.book != null && !wasNotified) openBookAddedDialog = true

    AnimatedVisibility(openBookAddedDialog) {
        BookAddedDialog({
            openBookAddedDialog = false
            wasNotified = true
        }, bookAdded)
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(BoneBackground)
            .padding(horizontal = 10.dp)
    ) {
        item {
            Canvas(modifier = Modifier.fillMaxWidth()) {
                val cornerRadius = CornerRadius(400f, 00f)
                val path = Path().apply {
                    addRoundRect(
                        RoundRect(
                            rect = Rect(
                                offset = Offset(-30f, 0f),
                                size = Size(size.width + 60f, 100.dp.toPx()),
                            ),
                            bottomLeft = cornerRadius,
                            bottomRight = cornerRadius,
                        )
                    )
                }
                drawPath(path, color = Orange)
            }
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    "Hey!",
                    color = Grey,
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = if (books.books.isNotEmpty()) "You have ${books.books.size} books added" else "Add a book to start tracking your reading",
                    color = Grey,
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(30.dp))
            }
            AddBookItem { visible = true }
            Spacer(modifier = Modifier.height(30.dp))
        }
        item {
            if (books.error.isNotBlank()) {
                Text(
                    text = books.error,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            } else if (books.isLoading) {
                CircularProgressIndicator()
            } else {
                NewReadLaterItem(
                    books.books,
                    { visible = true },
                    { navController.navigate(Screen.ReadLaterScreen.route) })
                Spacer(modifier = Modifier.height(30.dp))
            }
        }
        item {
            CardItem("Wishlist", "There are no books", Icons.Rounded.Favorite, modifierForCards)
            Spacer(modifier = Modifier.height(30.dp))
        }
        item {
            CardItem(
                "Book Calendar",
                "How much have you read this month?",
                Icons.Rounded.DateRange,
                modifierForCards
            )
            Spacer(modifier = Modifier.height(30.dp))
        }
        item {
            CollectionsTitle()
            Spacer(modifier = Modifier.height(30.dp))
        }
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .weight(5f)
                        .padding(horizontal = 10.dp)
                ) {
                    CardItem(
                        "Stopped books",
                        "There are no books",
                        Icons.Rounded.Pause,
                        modifierForCollections
                    )
                }
                Box(
                    modifier = Modifier
                        .weight(5f)
                        .padding(horizontal = 10.dp)
                        .clickable { navController.navigate(Screen.GaveUpScreen.route) }
                ) {
                    CardItem(
                        "Books you gave up on",
                        "There are no books",
                        Icons.Rounded.Flag,
                        modifierForCollections
                    )
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(30.dp))
            Box(modifier = Modifier.clickable { navController.navigate(Screen.LibraryScreen.route) })
            {
                CardItem(
                    "My Library", "There are ${books.books.size} books",
                    Icons.AutoMirrored.Rounded.MenuBook, modifierForCards
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Button(onClick = viewModel::deleteAllBooks) {
                Text("Delete books")
            }
        }

    }
    LaunchedEffect(key1 = true) {
        viewModel.refreshReadLater()
    }
}