package com.ailenaguino.booktracker.feature_book_detail.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ailenaguino.booktracker.ui.theme.BlueBackground
import com.ailenaguino.booktracker.ui.theme.BoneBackground

@Preview
@Composable
fun CurrentPageItem() {
    var sliderPosition by remember { mutableFloatStateOf(10f) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .offset(x = 50.dp, y = 0.dp)
            .shadow(10.dp, RoundedCornerShape(20.dp))
            .clip(RoundedCornerShape(20.dp))
            .background(Brush.verticalGradient(listOf(BoneBackground, Color.LightGray)))
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                "Pág. 10",
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray,
                fontSize = 40.sp
            )
            Text(
                " / 100 ",
                fontWeight = FontWeight.Light,
                color = Color.DarkGray,
                fontSize = 30.sp
            )
            Icon(Icons.Rounded.Edit, "editar", tint = Color.DarkGray)
        }

        Slider(
            sliderPosition,
            onValueChange = { sliderPosition = it },
            colors = SliderDefaults.colors(
                thumbColor = BlueBackground,
                activeTrackColor = BlueBackground,
                inactiveTrackColor = Color.Gray,
                activeTickColor = BlueBackground,
                inactiveTickColor = Color.Gray
            ),
            steps = 10,
            valueRange = 0f..100f,
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 40.dp, end = 50.dp)
                .offset(y = (-20).dp)
        )
    }
}