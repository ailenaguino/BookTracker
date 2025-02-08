package com.ailenaguino.booktracker.feature_add_book.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ailenaguino.booktracker.Screen
import com.ailenaguino.booktracker.feature_add_book.presentation.components.AddCover
import com.ailenaguino.booktracker.feature_add_book.presentation.components.InputItem
import com.ailenaguino.booktracker.feature_add_book.presentation.components.SaveFloatingButton
import com.ailenaguino.booktracker.feature_add_book.presentation.components.TagItem
import com.ailenaguino.booktracker.ui.theme.BoneBackground

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
        result.let {
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
                val background by animateColorAsState(targetValue = if(showButton) Color.White else Color.Transparent,
                    label = ""
                )
                val shadow by animateIntAsState(targetValue = if(showButton) 10 else 0, label = "")
                Box(modifier = Modifier
                    .offset(x = (-15).dp)
                    .shadow(shadow.dp, shape = RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 20.dp,
                        bottomEnd = 20.dp,
                        bottomStart = 0.dp
                    ))
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
                Box(modifier = Modifier.offset(x = 15.dp)) {
                    SaveFloatingButton(viewModel::onSaveBook)
                }
            }
            AnimatedVisibility(visible = showButton) {

            }
        }
        item {
            Text(
                "Añadir un libro",
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
            InputItem("Título", KeyboardType.Text, title, viewModel::onTitleChange)
        }
        item {
            InputItem("Autor", KeyboardType.Text, author, viewModel::onAuthorChange)
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "¿Qué tipo de libro lees?",
                fontSize = 16.sp,
                color = Color.DarkGray,
                modifier = Modifier.padding(vertical = 16.dp)
            )
            FlowRow(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                val typeBookOptions = listOf("libro de papel", "libro electrónico", "audio libro")
                typeBookOptions.forEach { text ->
                    TagItem(text, text == typeBook, viewModel::onTypeBookChange)
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "¿Cómo le gustaría registrar su progreso?",
                fontSize = 16.sp,
                color = Color.DarkGray,
                modifier = Modifier.padding(vertical = 16.dp)
            )
            FlowRow(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                val registerProgressOptions = listOf("página", "porcentaje", "episodio")
                registerProgressOptions.forEach { text ->
                    TagItem(text, text == registerProgress, viewModel::onRegisterProgressChange)
                }
            }
        }
        item {
            InputItem(
                "Total de páginas",
                KeyboardType.Number,
                totalPages,
                viewModel::onTotalPagesChange
            )
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "Estado",
                fontSize = 16.sp,
                color = Color.DarkGray,
                modifier = Modifier.padding(vertical = 16.dp)
            )
            FlowRow(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                val progressOptions =
                    listOf("leer más tarde", "leer ahora", "lo he leído todo", "me dí por vencido")
                progressOptions.forEach { text ->
                    TagItem(text, text == progress, viewModel::onProgressChange)
                }
            }
            Spacer(modifier = Modifier.height(18.dp))
        }
    }
}