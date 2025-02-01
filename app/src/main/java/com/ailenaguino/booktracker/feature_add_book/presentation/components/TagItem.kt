package com.ailenaguino.booktracker.feature_add_book.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TagItem(colorBackground: Color, colorText: Color, text: String) {
    Box(
        modifier = Modifier
            .padding(horizontal = 6.dp)
            .shadow(10.dp, shape = RoundedCornerShape(24.dp))
            .clip(RoundedCornerShape(24.dp))
            .background(colorBackground)

    ) {
        Text(
            text,
            fontSize = 16.sp,
            color = colorText,
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 18.dp)
        )
    }
}