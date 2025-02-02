package com.ailenaguino.booktracker.ui.sharedComponents

import android.view.Gravity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Book
import androidx.compose.material.icons.rounded.Keyboard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.DialogWindowProvider
import androidx.navigation.NavController
import com.ailenaguino.booktracker.Screen
import com.ailenaguino.booktracker.ui.theme.GreyTransp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddBookDialog(navController: NavController, onDismiss: () -> Unit) {
    ModalBottomSheet(onDismissRequest = { onDismiss() }, containerColor = Color.White) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp))
                .background(Color.White)
                .padding(15.dp)
        ) {
            Text(
                "¿Cómo quiere añadir el libro?",
                color = Color.Gray,
                fontSize = 18.sp,
                modifier = Modifier.padding(vertical = 10.dp)
            )
            HorizontalDivider(color = Color.Gray)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 14.dp)
                    .clickable { navController.navigate(Screen.AddBookManuallyScreen.route) },
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Rounded.Keyboard,
                    "entrada",
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .size(26.dp),
                    tint = GreyTransp
                )
                Text(
                    "Añadirlo manualmente",
                    color = Color.Gray,
                    fontSize = 16.sp
                )
            }
            HorizontalDivider(color = Color.Gray)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 14.dp)
                    .clickable { navController.navigate(Screen.SearchBookScreen.route) },
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Rounded.Book,
                    "google books",
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .size(26.dp),
                    tint = GreyTransp
                )
                Text(
                    "Buscarlo en Google Books",
                    color = Color.Gray,
                    fontSize = 16.sp
                )
            }
        }
    }
}