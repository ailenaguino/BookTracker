package com.ailenaguino.booktracker.feature_home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ailenaguino.booktracker.ui.theme.Grey
import com.ailenaguino.booktracker.ui.theme.GreyTransp
import com.ailenaguino.booktracker.ui.theme.ItemBackground

@Preview(showBackground = true, backgroundColor = 0xFFFFF4D0)
@Composable
fun CardItem() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(ItemBackground)
            .padding(20.dp)
    ) {
        Column {
            Text(
                text = "Lista de deseos",
                color = Grey,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )
            Text(text = "No hay libros.", color = Grey, style = MaterialTheme.typography.bodyMedium)
        }
        Icon(
            imageVector = Icons.Rounded.Favorite,
            contentDescription = "Corazón",
            tint = GreyTransp,
            modifier = Modifier
                .size(50.dp)
                .align(Alignment.BottomEnd)
                .rotate(-15f)
                .offset(10.dp, 15.dp)
        )
    }
}

@Composable
fun cardItem(title: String, subtitle: String, icon: ImageVector) {
    Box(
        modifier = Modifier
            .shadow(10.dp, RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(ItemBackground)
            .padding(20.dp)
    ) {
        Column {
            Text(
                text = title,
                color = Grey,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )
            Text(text = subtitle, color = Grey, style = MaterialTheme.typography.bodyMedium)
        }
        Icon(
            imageVector = icon,
            contentDescription = "Corazón",
            tint = GreyTransp,
            modifier = Modifier
                .size(50.dp)
                .align(Alignment.BottomEnd)
                .rotate(-15f)
                .offset(10.dp, 15.dp)
        )
    }
}