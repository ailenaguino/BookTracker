package com.ailenaguino.booktracker.feature_home.presentation.components

import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil3.compose.AsyncImage
import com.ailenaguino.booktracker.feature_add_book.domain.models.Book
import com.ailenaguino.booktracker.ui.theme.BlueBackground
import com.ailenaguino.booktracker.ui.theme.BoneBackgroundTransp
import com.ailenaguino.booktracker.ui.theme.Grey
import com.ailenaguino.booktracker.ui.theme.GreyTransp
import com.ailenaguino.booktracker.ui.theme.ItemBackground
import com.ailenaguino.booktracker.ui.theme.LightBlueTransp


@Composable
fun RoundedAddItem(onClick: () -> Unit, book: Book?) {
    Box(
        modifier = Modifier
            .size(80.dp)
            .border(BorderStroke(5.dp, Color(0x2C424242)), CircleShape)
            .clip(CircleShape)
            //.background(ItemBackground)
            .clickable { onClick() }
    ) {
        book?.let {
            AsyncImage(
                model = Uri.parse(it.cover),
                contentDescription = "portada",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )
        }
        if (book == null) {
            Icon(
                imageVector = Icons.Rounded.Add,
                contentDescription = "Añadir",
                modifier = Modifier.align(
                    Alignment.Center
                ),
                tint = Grey
            )
        }
    }
}

@Composable
fun ReadLaterTitle(title: String) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(LightBlueTransp)
                .height(15.dp)
                .align(Alignment.BottomCenter)
        )
        Text(
            text = title,
            color = Grey,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.align(Alignment.Center),
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ReadLaterItem(books: List<Book>, onItemClick: () -> Unit) {
    Column {
        ReadLaterTitle("Libros para leer más tarde")
        Spacer(modifier = Modifier.height(10.dp))
        if (books.size > 3) {
            Box(
                modifier = Modifier
                    .shadow(10.dp, RoundedCornerShape(8.dp))
                    .clip(RoundedCornerShape(8.dp))
                    .background(color = ItemBackground)
                    .padding(horizontal = 10.dp, vertical = 5.dp)
            ) {
                Text(
                    text = "Más",
                    style = MaterialTheme.typography.bodyLarge,
                    color = BlueBackground,
                    fontWeight = FontWeight.Medium
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))

        Box(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(50.dp))
                    .background(BoneBackgroundTransp)
                    .height(70.dp)
                    .align(Alignment.BottomCenter)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (books.size > 3) {
                    books.take(4).forEach {
                        RoundedAddItem(onItemClick, it)
                    }
                } else {
                    val emptyItems = 4 - books.size
                    books.forEach {
                        RoundedAddItem(onItemClick, it)
                    }
                    repeat(emptyItems) {
                        RoundedAddItem(onItemClick, null)
                    }
                }
            }
        }
    }
}