package com.ailenaguino.booktracker.feature_library.presentation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateIntAsState
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ailenaguino.booktracker.ui.sharedComponents.LibraryBookItem
import com.ailenaguino.booktracker.ui.theme.BlueBackground
import com.ailenaguino.booktracker.ui.theme.BoneBackground


@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class,
    ExperimentalLayoutApi::class
)
@Composable
fun LibraryScreen(viewModel: LibraryViewModel = hiltViewModel(), navController: NavController) {
    val listState = rememberLazyListState()
    val searchText by viewModel.searchText.collectAsState()
    val books by viewModel.books.collectAsState()

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
            val background by animateColorAsState(
                targetValue = if (showButton) Color.White else Color.Transparent,
                label = ""
            )
            val shadow by animateIntAsState(targetValue = if (showButton) 10 else 0, label = "")
            Box(
                modifier = Modifier
                    .offset(x = (-15).dp)
                    .shadow(
                        shadow.dp, shape = RoundedCornerShape(
                            topStart = 0.dp,
                            topEnd = 20.dp,
                            bottomEnd = 20.dp,
                            bottomStart = 0.dp
                        )
                    )
                    .background(background)
                    .clip(
                        RoundedCornerShape(
                            topStart = 0.dp,
                            topEnd = 20.dp,
                            bottomEnd = 20.dp,
                            bottomStart = 0.dp
                        )
                    )
                    .size(55.dp)
                    .clickable { navController.popBackStack() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.AutoMirrored.Rounded.ArrowBack,
                    "Atrás",
                    tint = Color.DarkGray,
                    modifier = Modifier
                        .size(30.dp)
                )
            }
        }
        item {
            Text(
                "Mi biblioteca",
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
                        onSearch = {},
                        expanded = false,
                        onExpandedChange = {},
                        placeholder = { Text("Busca algún libro", fontSize = 18.sp) },
                        leadingIcon = { Icon(Icons.Rounded.Search, "Buscar") },
                    )
                },
                expanded = false,
                onExpandedChange = { },
                colors = SearchBarColors(Color.White, Color.White),
                shadowElevation = 10.dp
            ) {

            }
        }
        item{
            if(books.isLoading){
                Box(modifier = Modifier.fillMaxWidth()) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = BlueBackground,
                        strokeWidth = 5.dp,
                        trackColor = Color.White
                    )
                }
            }
            if(books.error.isNotBlank()){
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
        item{
            FlowRow(verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()) {
                books.books.forEach{ book ->
                    LibraryBookItem(book.title, book.author, book.cover) { }
                }
            }
        }
    }
}