package com.ailenaguino.booktracker.ui.sharedComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.ailenaguino.booktracker.ui.theme.Orange


@Composable
fun LibraryBookItem(title: String, author: String, cover: String, onClick: () -> Unit) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.padding(horizontal = 3.dp).clickable { onClick() }) {
        Column(
            modifier = Modifier
                .padding(top = 30.dp)
                .height(190.dp)
                .width(110.dp)
                .shadow(10.dp, shape = RoundedCornerShape(5.dp))
                .background(Color.White)
                .clip(RoundedCornerShape(5.dp))
                .padding(bottom = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                title,
                color = Orange,
                style = MaterialTheme.typography.labelLarge,
                maxLines = 2,
                textAlign = TextAlign.Center
            )
            Text(
                author,
                color = Color.DarkGray,
                style = MaterialTheme.typography.labelMedium,
                textAlign = TextAlign.Center
            )
        }
        AsyncImage(
            model = cover,
            contentDescription = "portada",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .offset(y = (-30).dp)
                .height(130.dp)
                .width(90.dp)
                .border(3.dp, Color.White, RoundedCornerShape(10.dp))
                .shadow(10.dp, shape = RoundedCornerShape(10.dp))
        )
    }

}

@Preview(backgroundColor = 0xFF34558F, showBackground = true)
@Composable
fun LibraryBookItemPreview() {
    Box(modifier = Modifier.fillMaxSize()) {
        LibraryBookItem("Harry Potter y la orden del fénix", "J.K. Rowling", "", {})
    }
}