package com.ailenaguino.booktracker.ui.sharedComponents

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ArrowBack(showButton: Boolean, onClick:()->Unit){
    val background by animateColorAsState(
        targetValue = if (showButton) Color.White else Color.Transparent,
        label = ""
    )
    val shadow by animateIntAsState(targetValue = if (showButton) 10 else 0, label = "")
    Box(
        modifier = Modifier
            .offset(x = (-15).dp)
            .shadow(
                shadow.dp, shape = RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 20.dp,
                    bottomEnd = 20.dp,
                    bottomStart = 0.dp
                )
            )
            .background(background)
            .clip(
                RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 20.dp,
                    bottomEnd = 20.dp,
                    bottomStart = 0.dp
                )
            )
            .size(55.dp)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            Icons.AutoMirrored.Rounded.ArrowBack,
            "Atrás",
            tint = Color.DarkGray,
            modifier = Modifier
                .size(30.dp)
        )
    }
}