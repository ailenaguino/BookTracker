package com.ailenaguino.booktracker.feature_book_detail.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Book
import androidx.compose.material.icons.rounded.Flag
import androidx.compose.material.icons.rounded.Pause
import androidx.compose.material.icons.rounded.Stars
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ailenaguino.booktracker.common.Constants
import com.ailenaguino.booktracker.feature_book_detail.presentation.BookDetailViewModel
import com.ailenaguino.booktracker.ui.theme.GreyTransp
import com.ailenaguino.booktracker.ui.theme.Orange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StateModal(viewModel: BookDetailViewModel = hiltViewModel(), onDiscard: () -> Unit) {
    val state by viewModel.state
    ModalBottomSheet(onDismissRequest = { onDiscard() }, containerColor = Color.White) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "State",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(vertical = 10.dp),
            color = Orange
        )
        HorizontalDivider(color = GreyTransp)
        Column(modifier = Modifier.padding(horizontal = 10.dp)) {
            Row(modifier = Modifier.padding(vertical = 10.dp).clickable { viewModel.onStateChange(Constants.READ_NOW) }) {
                Icon(Icons.Rounded.Book, "read now", tint = if(state == Constants.READ_NOW) Orange else GreyTransp)
                Text(
                    Constants.READ_NOW,
                    color = if(state == Constants.READ_NOW) Orange else GreyTransp,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
            HorizontalDivider(color = GreyTransp)
            Row(modifier = Modifier.padding(vertical = 10.dp).clickable { viewModel.onStateChange(Constants.PAUSED) }) {
                Icon(Icons.Rounded.Pause, "paused", tint = if(state == Constants.PAUSED) Orange else GreyTransp)
                Text(
                    Constants.PAUSED,
                    color = if(state == Constants.PAUSED) Orange else GreyTransp,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
            HorizontalDivider(color = GreyTransp)
            Row(modifier = Modifier.padding(vertical = 10.dp).clickable { viewModel.onStateChange(Constants.GAVE_UP) }) {
                Icon(Icons.Rounded.Flag, "dropped", tint = if(state == Constants.GAVE_UP) Orange else GreyTransp)
                Text(
                    Constants.GAVE_UP,
                    color = if(state == Constants.GAVE_UP) Orange else GreyTransp,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
            HorizontalDivider(color = GreyTransp)
            Row(modifier = Modifier.padding(vertical = 10.dp).clickable { viewModel.onStateChange(Constants.FINISHED) }) {
                Icon(Icons.Rounded.Stars, "Completed", tint = if(state == Constants.FINISHED) Orange else GreyTransp)
                Text(
                    Constants.FINISHED,
                    color = if(state == Constants.FINISHED) Orange else GreyTransp,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
        }

    }
    }
}