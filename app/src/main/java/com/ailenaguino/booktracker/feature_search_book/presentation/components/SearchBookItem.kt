package com.ailenaguino.booktracker.feature_search_book.presentation.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.ailenaguino.booktracker.ui.theme.Grey

@Composable
fun SearchBookItem(title: String, author: String, cover: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(vertical = 20.dp)
                .shadow(12.dp, RoundedCornerShape(10.dp))
                .background(Color.White, RoundedCornerShape(10.dp))
        ) {
            Text(
                title,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 18.sp,
                color = Grey,
                maxLines = 3,
                modifier = Modifier
                    .padding(start = 80.dp)
                    .padding(10.dp)
            )
            Text(
                author,
                fontWeight = FontWeight.Light,
                style = MaterialTheme.typography.bodyMedium,
                color = Grey,
                modifier = Modifier
                    .padding(start = 90.dp)
            )
        }
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(cover)
                .crossfade(true)
                .build(),
            contentDescription = "Portada",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .width(70.dp)
                .offset(12.dp, (-5).dp)
                .shadow(12.dp, RoundedCornerShape(5.dp))
                .border(2.dp, Color.White, RoundedCornerShape(5.dp))
                .clip(RoundedCornerShape(5.dp))
        )
        Log.i("cover", cover)
    }
}