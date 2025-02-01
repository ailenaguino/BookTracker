package com.ailenaguino.booktracker.feature_search_book.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ailenaguino.booktracker.Screen
import com.ailenaguino.booktracker.feature_search_book.presentation.components.InputManuallyItem
import com.ailenaguino.booktracker.feature_search_book.presentation.components.SearchBookBar
import com.ailenaguino.booktracker.feature_search_book.presentation.components.SearchBookItem
import com.ailenaguino.booktracker.ui.theme.BlueBackground
import com.ailenaguino.booktracker.ui.theme.BoneBackground
import com.ailenaguino.booktracker.ui.theme.GreyTransp

@Composable
fun SearchBookScreen(viewModel: SearchBookViewModel = hiltViewModel(), navController: NavController) {
    val state = viewModel.books.value
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(BoneBackground)
            .padding(15.dp)
    ) {
        item {
            Icon(
                Icons.AutoMirrored.Rounded.ArrowBack,
                "Atrás",
                tint = Color.DarkGray,
                modifier = Modifier.size(30.dp)
            )
        }
        item {
            Spacer(Modifier.height(15.dp))
            Text(
                "¡Busca un libro!",
                color = GreyTransp,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(15.dp))
        }
        item {
            SearchBookBar(viewModel)
            Spacer(Modifier.height(25.dp))
        }
        item{
            if(state.books.isNotEmpty() && !state.isLoading){
                InputManuallyItem { navController.navigate(Screen.AddBookManuallyScreen.route) }
                Spacer(Modifier.height(50.dp))
            }
        }
        items(state.books) { book ->
            SearchBookItem(book.title, book.author[0], book.cover)
        }
        item {
            if (state.error.isNotBlank()) {
                Text(
                    text = state.error,
                    color = Color.Red,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                )
            }
            if (state.isLoading) {
                Box(modifier= Modifier.fillMaxWidth()) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = BlueBackground,
                        strokeWidth = 5.dp,
                        trackColor = Color.White
                    )
                }
            }
        }
    }
}