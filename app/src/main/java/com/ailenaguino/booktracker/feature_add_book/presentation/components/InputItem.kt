package com.ailenaguino.booktracker.feature_add_book.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ailenaguino.booktracker.ui.theme.BlueBackground
import com.ailenaguino.booktracker.ui.theme.BoneBackgroundTransp
import com.ailenaguino.booktracker.ui.theme.GreyTransp
import com.ailenaguino.booktracker.ui.theme.LightBlueTransp

@Composable
fun InputItem(text: String) {
    Box() {
        Box(modifier = Modifier.fillMaxWidth().height(18.dp).background(LightBlueTransp).align(Alignment.BottomCenter))

        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text(text, fontSize = 18.sp) },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedTextColor = Color.White.copy(alpha = 0f),
                unfocusedBorderColor = Color.White.copy(alpha = 0f),
                unfocusedLabelColor = Color.DarkGray,
                unfocusedLeadingIconColor = Color.White.copy(alpha = 0f),
                focusedBorderColor = Color.White.copy(alpha = 0f),
                focusedLabelColor = BlueBackground
            )
        )
    }
}