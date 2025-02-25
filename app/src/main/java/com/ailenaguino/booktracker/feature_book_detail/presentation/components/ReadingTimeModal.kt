package com.ailenaguino.booktracker.feature_book_detail.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ailenaguino.booktracker.feature_book_detail.presentation.BookDetailViewModel
import com.ailenaguino.booktracker.ui.theme.Orange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReadingTimeModal(viewModel: BookDetailViewModel = hiltViewModel(), onDiscard: () -> Unit) {
    val hours by viewModel.hours
    val minutes by viewModel.minutes
    ModalBottomSheet(onDismissRequest = { onDiscard() }, containerColor = Color.White) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Add the reading time",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(vertical = 10.dp),
                color = Orange
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                TimeTextField("Hours", hours) { viewModel.onHoursChange(it) }
                TimeTextField("Minutes", minutes) { viewModel.onMinutesChange(it) }
            }
            OutlinedButton(
                {
                    onDiscard()
                    viewModel.hoursSaved.intValue = if (hours.isNotBlank()) hours.toInt() else 0
                    viewModel.minutesSaved.intValue = if (minutes.isNotBlank()) minutes.toInt() else 0
                },
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 18.dp),
                border = BorderStroke(1.dp, Orange)
            ) { Text("Save", color = Orange) }
        }
    }
}

@Composable
fun TimeTextField(text: String, value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        label = { Text(text, fontSize = 18.sp) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedTextColor = Color.DarkGray,
            unfocusedBorderColor = Orange,
            unfocusedLabelColor = Orange,
            unfocusedLeadingIconColor = Orange,
            focusedBorderColor = Orange,
            focusedLabelColor = Orange,
            focusedTextColor = Color.DarkGray
        ),
        modifier = Modifier.width(100.dp)
    )
}