package com.ailenaguino.booktracker.feature_add_book.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ailenaguino.booktracker.ui.theme.LightBlueTransp
import com.ailenaguino.booktracker.ui.theme.Orange

@Composable
fun InputItem(
    text: String,
    keyboardType: KeyboardType,
    value: String,
    onValueChange: (String) -> Unit,
) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        label = { Text(text, fontSize = 18.sp) },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedTextColor = Color.DarkGray,
            unfocusedBorderColor = Orange,
            unfocusedLabelColor = Orange,
            unfocusedLeadingIconColor = Orange,
            focusedBorderColor = Orange,
            focusedLabelColor = Orange,
            focusedTextColor = Color.DarkGray
        ),
        modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp)
    )
}