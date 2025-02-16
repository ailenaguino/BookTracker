package com.ailenaguino.booktracker.feature_book_detail.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.ailenaguino.booktracker.ui.theme.BlueBackground

@Composable
fun CoverItem(cover:String, title:String, author:String, day:String, date:String) {

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(230.dp)
            .offset(x = (-50).dp, y = 0.dp)
            .shadow(10.dp, RoundedCornerShape(20.dp))
            .clip(RoundedCornerShape(20.dp))
            .background(Brush.verticalGradient(listOf(BlueBackground, BlueBackground)))
    ) {
        AsyncImage(
            model = cover,
            contentDescription = "portada",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(180.dp)
                .width(120.dp)
                .offset(x = 50.dp)
                .shadow(10.dp, RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp))
                .border(2.dp, Color.White, RoundedCornerShape(10.dp))
        )
        Column(modifier = Modifier
            .offset(x = 70.dp)
            .align(Alignment.CenterVertically)) {
            Text(
                title,
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp,
                modifier = Modifier.width(300.dp).padding(end = 70.dp)
            )
            Text(
                author,
                color = Color.White,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Light,
                letterSpacing = 1.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            DayItem(day, date)
        }

    }
}
