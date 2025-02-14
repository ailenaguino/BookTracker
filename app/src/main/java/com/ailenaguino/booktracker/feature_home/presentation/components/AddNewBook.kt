package com.ailenaguino.booktracker.feature_home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ailenaguino.booktracker.ui.theme.Grey
import com.ailenaguino.booktracker.ui.theme.ItemBackground
import com.ailenaguino.booktracker.ui.theme.LightBlue

@Composable
fun AddBookItem(onItemClick: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(20.dp)
            .shadow(10.dp, RoundedCornerShape(bottomEnd = 80.dp))
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(bottomEnd = 80.dp))
            .background(ItemBackground).clickable { onItemClick() }
    )
    {
        Icon(
            imageVector = Icons.Rounded.Add,
            "Añadir",
            tint = LightBlue,
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.BottomEnd)
                .absoluteOffset(55.dp, 55.dp)
        )
        Column(
            modifier = Modifier
                .padding(30.dp, 30.dp, 0.dp, 0.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Añadir un libro",
                color = Grey,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "¿Hay algún libro que estes leyendo?",
                color = Grey,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

