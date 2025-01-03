package com.ailenaguino.booktracker.feature_search_book.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ailenaguino.booktracker.feature_search_book.presentation.SearchBookViewModel
import com.ailenaguino.booktracker.ui.theme.Grey

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBookBar(viewModel: SearchBookViewModel = hiltViewModel()) {
    val searchText by viewModel.searchText.collectAsState()

    SearchBar(
        query = searchText,
        onQueryChange = {viewModel.onSearchTextChange(it)},
        onSearch = {viewModel.getBooks()},
        active = false,
        onActiveChange = { },
        placeholder = {Text("Introduzca el título", fontSize = 18.sp, color = Grey)},
        leadingIcon = { Icon(Icons.Rounded.Search, "Buscar", tint = Grey)},
        colors = SearchBarColors(Color.White, Color.White),
        shadowElevation = 10.dp
    ) {}
}