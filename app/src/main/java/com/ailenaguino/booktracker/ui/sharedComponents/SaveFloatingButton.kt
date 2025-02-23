package com.ailenaguino.booktracker.ui.sharedComponents

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ailenaguino.booktracker.ui.theme.Orange

@Composable
fun SaveFloatingButton(onClick:()->Unit){
    ExtendedFloatingActionButton(
        onClick = { onClick() },
        icon = { Icon(Icons.Rounded.Check, "Save", tint = Orange) },
        text = { Text(text = "Guardar", color = Color.DarkGray) },
        containerColor = Color.White,
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 0.dp, bottomEnd = 0.dp, bottomStart = 20.dp)
    )

}