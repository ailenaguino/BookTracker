package com.ailenaguino.booktracker.feature_add_book.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.ailenaguino.booktracker.ui.theme.Orange

@Composable
fun TagItem(text: String, isSelected: Boolean, onSelection: (String) -> Unit) {
    Box(
        modifier = Modifier
            .padding(horizontal = 4.dp)
            .shadow(10.dp, shape = RoundedCornerShape(24.dp))
            .clip(RoundedCornerShape(24.dp))
            .background(if (isSelected) Orange else Color.White)
            .clickable { onSelection(text) }

    ) {
        Text(
            text,
            fontSize = 14.sp,
            color = if (isSelected) Color.White else Color.DarkGray,
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 18.dp)
        )
    }
}