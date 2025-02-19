package com.ailenaguino.booktracker.feature_home.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ailenaguino.booktracker.ui.theme.BoneBackgroundTransp
import com.ailenaguino.booktracker.ui.theme.Grey
import com.ailenaguino.booktracker.ui.theme.LightBlue

@Composable
fun CardItem(title: String, subtitle: String, icon: ImageVector, modifier: Modifier) {
    Box(
        modifier = modifier
    ) {
        Column {
            Text(
                text = title,
                color = Grey,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            Text(text = subtitle, color = Grey, style = MaterialTheme.typography.bodyMedium)
        }
        Icon(
            imageVector = icon,
            contentDescription = "icon",
            tint = BoneBackgroundTransp,
            modifier = Modifier
                .size(50.dp)
                .align(Alignment.BottomEnd)
                .rotate(-15f)
                .offset(10.dp, 15.dp)
        )
    }
}