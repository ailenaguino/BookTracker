package com.ailenaguino.booktracker.feature_home.presentation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ailenaguino.booktracker.Screen
import com.ailenaguino.booktracker.feature_home.presentation.components.AddBookItem
import com.ailenaguino.booktracker.feature_home.presentation.components.CollectionsTitle
import com.ailenaguino.booktracker.feature_home.presentation.components.ReadLaterItem
import com.ailenaguino.booktracker.feature_home.presentation.components.cardItem
import com.ailenaguino.booktracker.ui.theme.BlueBackground
import com.ailenaguino.booktracker.ui.theme.BoneBackground
import com.ailenaguino.booktracker.ui.theme.BoneBackgroundTransp
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
fun HomeScreen(navController: NavController) {
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .background(BoneBackground)
        .padding(horizontal = 10.dp)
    ) {
        item {
            Canvas(modifier = Modifier.fillMaxWidth()) {
                val cornerRadius = CornerRadius(800f, 250f)
                val path = Path().apply {
                    addRoundRect(
                        RoundRect(
                            rect = Rect(
                                offset = Offset(-30f, 0f),
                                size = Size(size.width+60f, 300.dp.toPx()),
                            ),
                            bottomLeft = cornerRadius,
                            bottomRight = cornerRadius,
                        )
                    )
                }
                drawPath(path, color = BlueBackground)
            }
            Column(modifier = Modifier.padding(10.dp)) {
                Text(
                    "¡Hola! :)",
                    color = BoneBackgroundTransp,
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    "Primero, agregue un libro.",
                    color = BoneBackgroundTransp,
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(30.dp))
            }
            AddBookItem { navController.navigate(Screen.SearchBookScreen.route) }
            Spacer(modifier = Modifier.height(30.dp))
        }
        item {
            ReadLaterItem()
            Spacer(modifier = Modifier.height(30.dp))
        }
        item {
            cardItem("Lista de deseos", "No hay libros.", Icons.Rounded.Favorite, modifierForCards)
            Spacer(modifier = Modifier.height(30.dp))
        }
        item {
            cardItem("Calendario de libros", "¿Cuánto has leído este mes?", Icons.Rounded.DateRange, modifierForCards)
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
                Box(modifier = Modifier
                    .weight(5f)
                    .padding(horizontal = 10.dp)) {
                    cardItem("Libros en pausa", "No hay libros", Icons.Rounded.Pause, modifierForCollections)
                }
                Box(modifier = Modifier
                    .weight(5f)
                    .padding(horizontal = 10.dp)) {
                    cardItem("Libros que dejaste de leer", "No hay libros", Icons.Rounded.Flag, modifierForCollections)
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(30.dp))
            cardItem("Mi biblioteca", "No hay libros.",
                Icons.AutoMirrored.Rounded.MenuBook, modifierForCards
            )
            Spacer(modifier = Modifier.height(30.dp))
        }

    }
}