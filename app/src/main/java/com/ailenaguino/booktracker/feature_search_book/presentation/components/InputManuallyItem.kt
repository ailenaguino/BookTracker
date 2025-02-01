package com.ailenaguino.booktracker.feature_search_book.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ailenaguino.booktracker.ui.theme.BlueBackground
import com.ailenaguino.booktracker.ui.theme.Grey

@Composable
fun InputManuallyItem(onItemClick : () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White.copy(alpha = 0.5f))
            .clickable { onItemClick() }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(10.dp)
                .background(BlueBackground, shape = RoundedCornerShape(5.dp))
                .size(50.dp, 50.dp)
        ) {
            Icon(
                Icons.Rounded.Add,
                "Añadir",
                tint = Color.White.copy(alpha = 0.5f),
                modifier = Modifier.size(50.dp)
            )
        }
        Column(modifier = Modifier.padding(10.dp)) {
            Text("¿No tenemos el libro que buscas?", color = Grey, fontSize = 12.sp)
            Spacer(modifier = Modifier.height(3.dp))
            Text("Ingréselo usted mismo", color = Color.Black, fontSize = 18.sp)
        }
    }
}