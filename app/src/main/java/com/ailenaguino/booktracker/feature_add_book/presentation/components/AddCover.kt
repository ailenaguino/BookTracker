package com.ailenaguino.booktracker.feature_add_book.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CameraAlt
import androidx.compose.material.icons.rounded.Image
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun AddCover() {
    Box(
        modifier = Modifier
            .height(180.dp)
            .width(110.dp)
            .shadow(10.dp, shape = RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
    ) {
        Icon(
            Icons.Rounded.Image,
            "Galery",
            tint = Color.Gray,
            modifier = Modifier.align(Alignment.Center)
        )
        Icon(
            Icons.Rounded.CameraAlt,
            "Camera",
            tint = Color.DarkGray,
            modifier = Modifier.align(Alignment.BottomEnd).padding(8.dp)
        )
    }
}