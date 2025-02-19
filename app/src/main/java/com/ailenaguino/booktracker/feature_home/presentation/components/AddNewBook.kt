package com.ailenaguino.booktracker.feature_home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.BookmarkAdd
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ailenaguino.booktracker.ui.theme.BoneBackgroundTransp
import com.ailenaguino.booktracker.ui.theme.Grey
import com.ailenaguino.booktracker.ui.theme.ItemBackground

@Composable
fun AddBookItem(onItemClick: () -> Unit) {
    Box(
        modifier = Modifier
            .offset(x = ((-30).dp))
            .shadow(10.dp, RoundedCornerShape(bottomEnd = 50.dp, topEnd = 50.dp))
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomEnd = 50.dp, topEnd = 50.dp))
            .background(ItemBackground).clickable { onItemClick() }
            .padding(start = 30.dp)

    )
    {
        Icon(
            imageVector = Icons.Rounded.BookmarkAdd,
            "Add",
            tint = BoneBackgroundTransp,
            modifier = Modifier
                .size(50.dp)
                .align(Alignment.CenterEnd)
                .rotate(-15f)
        )
        Column(
            modifier = Modifier
                .padding(30.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Add a book",
                color = Grey,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Is there any book you want to add?",
                color = Grey,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview
@Composable
fun AddBookItemPreview() {
    AddBookItem {}
}

