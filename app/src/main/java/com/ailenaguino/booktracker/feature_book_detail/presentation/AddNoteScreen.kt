package com.ailenaguino.booktracker.feature_book_detail.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Bookmark
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ailenaguino.booktracker.feature_book_detail.presentation.components.NoteCategory
import com.ailenaguino.booktracker.ui.theme.BoneBackground
import com.ailenaguino.booktracker.ui.theme.Orange
import java.util.Calendar

@Preview
@Composable
fun AddNoteScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(BoneBackground)
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Orange.copy(alpha = 0.4f))
                    .padding(20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    Icons.Rounded.Close,
                    "Close",
                    tint = Color.White,
                    modifier = Modifier.size(32.dp)
                )
                Icon(
                    Icons.Rounded.Check,
                    "Save",
                    tint = Color.White,
                    modifier = Modifier.size(32.dp)
                )
            }
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                NoteCategory(Icons.Rounded.Bookmark, "Book fragment", Color.Blue)
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.White.copy(alpha = 0.8f))
                        .border(1.dp, Orange, RoundedCornerShape(16.dp))
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                ) {
                    Text(
                        text = "Page",
                        color = Orange,
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier.padding(horizontal = 5.dp)
                    )
                }
            }
        }
        item {
            Text(
                getCurrentDay(),
                color = Color.DarkGray,
                modifier = Modifier.padding(horizontal = 20.dp),
                style = MaterialTheme.typography.titleMedium
            )
        }
        item {
            TextField(
                "",
                onValueChange = {},
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent),
                placeholder = { Text("Write your note here...") },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent
                )
            )
        }
    }

}

fun getCurrentDay(): String {
    val calendar = Calendar.getInstance()
    val minute = if (calendar.get(Calendar.MINUTE) < 10) {
        "0" + calendar.get(Calendar.MINUTE)
    } else {
        calendar.get(Calendar.MINUTE)
    }
    val date = "${calendar.get(Calendar.DAY_OF_MONTH)}/" +
            "${calendar.get(Calendar.MONTH)}/" +
            "${calendar.get(Calendar.YEAR)} | " +
            "${calendar.get(Calendar.HOUR_OF_DAY)}:" +
            "$minute"
    return date
}