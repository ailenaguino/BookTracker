package com.ailenaguino.booktracker.feature_book_detail.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ailenaguino.booktracker.ui.theme.Orange
import kotlin.math.roundToInt

@Composable
fun CurrentPageItem(pages: Float, currentPage: Float) {
    var sliderPosition by remember { mutableFloatStateOf(currentPage) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp)
            .offset(x = 50.dp, y = 0.dp)
            .shadow(10.dp, RoundedCornerShape(20.dp))
            .clip(RoundedCornerShape(20.dp))
            .background(Brush.verticalGradient(listOf(Color.White, Color.White)))
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                "Page ${currentPage.roundToInt()}",
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray,
                fontSize = 30.sp
            )
            Text(
                " / ${pages.roundToInt()} ",
                fontWeight = FontWeight.Light,
                color = Color.DarkGray,
                fontSize = 20.sp
            )
            Icon(Icons.Rounded.Edit, "edit", tint = Color.DarkGray)
        }

        Slider(
            sliderPosition,
            onValueChange = { sliderPosition = it },
            colors = SliderDefaults.colors(
                disabledThumbColor = Orange,
                disabledActiveTrackColor = Orange,
                disabledInactiveTrackColor = Color.Gray,
                activeTickColor = Orange,
                disabledInactiveTickColor = Color.Gray
            ),
            enabled = false,
            steps = 0,
            valueRange = 0f..pages,
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 40.dp, end = 50.dp)
                .offset(y = (-20).dp),
            )
    }
}