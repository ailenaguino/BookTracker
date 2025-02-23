package com.ailenaguino.booktracker.feature_book_detail.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ailenaguino.booktracker.ui.theme.Grey
import com.ailenaguino.booktracker.ui.theme.ItemBackground

@Composable
fun SaveLectureCard(
    icon: ImageVector,
    title: String,
    subtitle: String,
    miniTitle: String,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 15.dp, horizontal = 20.dp)
            .shadow(10.dp, RoundedCornerShape(10.dp))
            .background(ItemBackground)
            .padding(20.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Column() {
            Text(subtitle, color = Grey)
            if (miniTitle.isNotBlank()) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(title, color = Grey, fontWeight = FontWeight.Bold, fontSize = 30.sp)
                    Text(
                        miniTitle,
                        color = Grey,
                        modifier = Modifier.padding(horizontal = 10.dp),
                        fontSize = 18.sp
                    )
                }
            } else {
                Text(title, color = Grey, fontWeight = FontWeight.Bold, fontSize = 30.sp)
            }

        }
        Icon(icon, "timer", tint = Grey, modifier = Modifier.size(50.dp))
    }
}
