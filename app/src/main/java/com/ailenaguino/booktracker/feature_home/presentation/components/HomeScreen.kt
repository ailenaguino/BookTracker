package com.ailenaguino.booktracker.feature_home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ailenaguino.booktracker.ui.theme.BlueBackground
import com.ailenaguino.booktracker.ui.theme.BoneBackground
import com.ailenaguino.booktracker.ui.theme.BoneBackgroundTransp


@Preview(showBackground = true)
@Composable
fun HomeScreen() {

    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .background(BoneBackground)
        .drawBehind {
            val cornerRadius = CornerRadius(800f, 250f)
            val path = Path().apply {
                addRoundRect(
                    RoundRect(
                        rect = Rect(
                            offset = Offset(0f, 0f),
                            size = Size(size.width, 300.dp.toPx()),
                        ),
                        bottomLeft = cornerRadius,
                        bottomRight = cornerRadius,
                    )
                )
            }
            drawPath(path, color = BlueBackground)
        }
        .padding(10.dp)) {
        item {
            Column(modifier= Modifier.padding(10.dp)){
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
        }
        item {
            AddBookItem()
        }
        item {
            ReadLaterItem()
            Spacer(modifier = Modifier.height(30.dp))
        }
        item {
            cardItem("Lista de deseos", "No hay libros.", Icons.Rounded.Favorite)
            Spacer(modifier = Modifier.height(30.dp))
        }
        item {
            cardItem("Calendario de libros", "¿Cuánto has leído este mes?", Icons.Rounded.DateRange)
        }

    }
}