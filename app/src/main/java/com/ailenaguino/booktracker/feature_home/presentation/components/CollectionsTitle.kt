package com.ailenaguino.booktracker.feature_home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ailenaguino.booktracker.ui.theme.ItemBackground
import com.ailenaguino.booktracker.ui.theme.Orange

@Composable
fun CollectionsTitle(){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(modifier = Modifier.width(200.dp)) {
            ReadLaterTitle("Collections")
        }
        Box(
            modifier = Modifier
                .shadow(10.dp, RoundedCornerShape(8.dp))
                .clip(RoundedCornerShape(8.dp))
                .background(color = ItemBackground)
                .padding(horizontal = 10.dp, vertical = 5.dp)
        ) {
            Text(
                text = "More",
                style = MaterialTheme.typography.titleLarge,
                color = Orange,
                fontWeight = FontWeight.Medium
            )
        }
    }
}