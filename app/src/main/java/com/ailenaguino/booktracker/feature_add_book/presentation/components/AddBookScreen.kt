package com.ailenaguino.booktracker.feature_add_book.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ailenaguino.booktracker.ui.theme.BlueBackground
import com.ailenaguino.booktracker.ui.theme.BoneBackground

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AddBookScreen(navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(BoneBackground)
            .padding(15.dp)
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    Icons.AutoMirrored.Rounded.ArrowBack,
                    "Atrás",
                    tint = Color.DarkGray,
                    modifier = Modifier.size(30.dp).clickable { navController.popBackStack() }
                )
                Box(modifier = Modifier.offset(x = 15.dp)) {
                    SaveFloatingButton()
                }
            }
        }
        item {
            Text(
                "Añadir un libro",
                color = Color.DarkGray,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                modifier = Modifier.padding(vertical = 16.dp)
            )
        }
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp),
                contentAlignment = Alignment.Center
            ) {
                AddCover()
            }
        }
        item {
            InputItem("Título")
        }
        item {
            InputItem("Autor")
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "¿Qué tipo de libro lees?",
                fontSize = 16.sp,
                color = Color.DarkGray,
                modifier = Modifier.padding(vertical = 16.dp)
            )
            FlowRow(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                TagItem(BlueBackground, Color.White, "libro de papel")
                TagItem(Color.White, Color.DarkGray, "libro electrónico")
                TagItem(Color.White, Color.DarkGray, "audio libro")
            }
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "¿Cómo le gustaría registrar su progreso?",
                fontSize = 16.sp,
                color = Color.DarkGray,
                modifier = Modifier.padding(vertical = 16.dp)
            )
            FlowRow(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                TagItem(BlueBackground, Color.White, "página")
                TagItem(Color.White, Color.DarkGray, "porcentaje")
                TagItem(Color.White, Color.DarkGray, "episodio")
            }
        }
        item {
            InputItem("Total de páginas")
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "Estado",
                fontSize = 16.sp,
                color = Color.DarkGray,
                modifier = Modifier.padding(vertical = 16.dp)
            )
            FlowRow(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                TagItem(BlueBackground, Color.White, "leer más tarde")
                TagItem(Color.White, Color.DarkGray, "leer ahora")
                TagItem(Color.White, Color.DarkGray, "lo he leído todo")
                TagItem(Color.White, Color.DarkGray, "me dí por vencido")
            }
        }
    }
}