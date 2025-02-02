package com.ailenaguino.booktracker.feature_add_book.presentation.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ailenaguino.booktracker.ui.theme.BlueBackground

@Preview
@Composable
fun SaveFloatingButton(){
    ExtendedFloatingActionButton(
        onClick = { },
        icon = { Icon(Icons.Rounded.Check, "Save", tint = BlueBackground) },
        text = { Text(text = "Guardar", color = Color.DarkGray) },
        containerColor = Color.White,
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 0.dp, bottomEnd = 0.dp, bottomStart = 20.dp)
    )

}