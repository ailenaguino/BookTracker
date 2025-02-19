package com.ailenaguino.booktracker.feature_read_later.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarColors
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ailenaguino.booktracker.ui.sharedComponents.ArrowBack
import com.ailenaguino.booktracker.ui.sharedComponents.LibraryBookItem
import com.ailenaguino.booktracker.ui.theme.Orange
import com.ailenaguino.booktracker.ui.theme.BoneBackground
import com.ailenaguino.booktracker.ui.theme.Grey

@OptIn(ExperimentalFoundationApi::class, ExperimentalLayoutApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
fun ReadLaterScreen(viewModel: ReadLaterViewModel = hiltViewModel(), navController: NavController){
    val listState = rememberLazyListState()
    val searchText by viewModel.searchText.collectAsState()
    val books by viewModel.books.collectAsState()
    val filteredBooks by viewModel.filteredBooks.collectAsState()

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
            ArrowBack(showButton) { navController.popBackStack() }
        }
        item {
            Text(
                "Libros para leer más tarde",
                color = Color.DarkGray,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                modifier = Modifier.padding(vertical = 16.dp)
            )
            Text("Tienes ${books.books.size} libros", color = Color.DarkGray)
            Spacer(modifier = Modifier.size(20.dp))
        }
        item {
            SearchBar(
                inputField = {
                    SearchBarDefaults.InputField(
                        query = searchText,
                        onQueryChange = viewModel::onSearchTextChange,
                        onSearch = { viewModel.searchBook(it) },
                        expanded = false,
                        onExpandedChange = {},
                        placeholder = { Text("Busca algún libro", fontSize = 18.sp) },
                        leadingIcon = { Icon(Icons.Rounded.Search, "Buscar", tint = Grey) },
                        trailingIcon = {
                            if (searchText.isNotEmpty()) {
                                Icon(
                                    Icons.Rounded.Close,
                                    "Cerrar",
                                    tint = Grey,
                                    modifier = Modifier.clickable { viewModel.onSearchTextChange("") })
                            }
                        },
                    )
                },
                expanded = false,
                onExpandedChange = { },
                colors = SearchBarColors(Color.White, Color.White),
                shadowElevation = 9.dp
            ) {

            }
        }
        item {
            if (books.isLoading) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = Orange,
                        strokeWidth = 5.dp,
                        trackColor = Color.White
                    )
                }
            }
            if (books.error.isNotBlank()) {
                Text(
                    text = books.error,
                    color = Color.Red,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                )
            }
        }
        item {
            FlowRow(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                filteredBooks.forEach { book ->
                    LibraryBookItem(book.title, book.author, book.cover) { }
                }
            }
        }
    }
}