package com.ailenaguino.booktracker.feature_home.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Book
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.ailenaguino.booktracker.common.BookState
import com.ailenaguino.booktracker.ui.theme.BlueBackground

@Composable
fun BookAddedDialog(onDismiss:() -> Unit, bookAdded: BookState){
    AlertDialog(
        containerColor = Color.White,
        icon = {
            Icon(Icons.Rounded.Book, "Libro Añadido", tint = BlueBackground)
        },
        title = {
            Text(text = "Libro Añadido", color = BlueBackground)
        },
        text = {
            Text(
                text = bookAdded.book!!.title + " por " + bookAdded.book.author,
                textAlign = TextAlign.Center,
                color = Color.DarkGray
            )
        },
        onDismissRequest = {
            onDismiss()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onDismiss()
                }
            ) {
                Text("Listo", color = BlueBackground)
            }
        }
    )
}