package com.ailenaguino.booktracker.feature_home.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ailenaguino.booktracker.ui.theme.BoneBackgroundTransp
import com.ailenaguino.booktracker.ui.theme.Grey
import com.ailenaguino.booktracker.ui.theme.GreyTransp
import com.ailenaguino.booktracker.ui.theme.ItemBackground
import com.ailenaguino.booktracker.ui.theme.LightBlueTransp


@Preview
@Composable
fun RoundedAddItem() {
    Box(
        modifier = Modifier
            .size(80.dp)
            .border(BorderStroke(5.dp, Color(0x2C424242)), CircleShape)
            .clip(CircleShape)
            .background(ItemBackground)
    ) {
        Icon(
            imageVector = Icons.Rounded.Add,
            contentDescription = "Añadir",
            modifier = Modifier.align(
                Alignment.Center
            ),
            tint = Grey
        )
    }
}

@Preview
@Composable
fun ReadLaterTitle() {
    Box(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(LightBlueTransp)
                .height(15.dp)
                .align(Alignment.BottomCenter)
        )
        Text(
            text = "Libros para leer más tarde",
            color = Grey,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.align(Alignment.Center),
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFF4D0)
@Composable
fun ReadLaterItem() {
    Column {
        ReadLaterTitle()

        Spacer(modifier = Modifier.height(20.dp))

        Box(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(50.dp))
                    .background(BoneBackgroundTransp)
                    .height(70.dp)
                    .align(Alignment.BottomCenter)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                RoundedAddItem()
                RoundedAddItem()
                RoundedAddItem()
                RoundedAddItem()
            }
        }
    }
}