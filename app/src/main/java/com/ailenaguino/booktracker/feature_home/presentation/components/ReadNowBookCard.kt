package com.ailenaguino.booktracker.feature_home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.ailenaguino.booktracker.feature_book_detail.presentation.components.DayItem
import com.ailenaguino.booktracker.ui.theme.BoneBackground
import com.ailenaguino.booktracker.ui.theme.ItemBackground
import com.ailenaguino.booktracker.ui.theme.LightBlue


@Preview
@Composable
fun ReadNowPreview() {
    ReadNowBookCard(
        15,
        170,
        "Harry Potter y el caliz del fuego",
        "J.K Rowling",
        "",
        "10",
        "15/03/2023",
        {})
}

@Composable
fun ReadNowBookCard(
    currentPage: Int,
    pages: Int,
    title: String,
    author: String,
    cover: String,
    sessions: String,
    date: String,
    onClick: () -> Unit,
) {
    var sliderPosition by remember { mutableFloatStateOf(currentPage.toFloat()) }
    Box(
        modifier = Modifier
            .padding(20.dp)
            .shadow(10.dp, RoundedCornerShape(10.dp))
            .width(300.dp)
            .height(280.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(ItemBackground)
            .padding(20.dp)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                title,
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp,
                maxLines = 3,
                textAlign = TextAlign.Center
            )
            Text(
                author,
                maxLines = 2,
                color = Color.White,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Light,
                letterSpacing = 1.sp
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                AsyncImage(
                    model = cover,
                    contentDescription = "cover",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(140.dp)
                        .width(90.dp)
                        .shadow(10.dp, RoundedCornerShape(10.dp))
                        .clip(RoundedCornerShape(10.dp))
                        .border(2.dp, Color.White, RoundedCornerShape(10.dp))
                )
                Spacer(modifier = Modifier.width(20.dp))
                Column(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(70.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                "Page $currentPage",
                                fontWeight = FontWeight.Bold,
                                color = BoneBackground,
                                fontSize = 20.sp
                            )
                            Text(
                                " / $pages",
                                fontWeight = FontWeight.Light,
                                color = BoneBackground,
                                fontSize = 16.sp
                            )
                        }

                        Slider(
                            sliderPosition,
                            onValueChange = { sliderPosition = it },
                            colors = SliderDefaults.colors(
                                disabledThumbColor = LightBlue,
                                disabledActiveTrackColor = LightBlue,
                                disabledInactiveTrackColor = BoneBackground,
                                activeTickColor = LightBlue,
                                disabledInactiveTickColor = BoneBackground
                            ),
                            enabled = false,
                            steps = 0,
                            valueRange = 0f..pages.toFloat(),
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    DayItem(sessions, date)
                }
            }
        }
    }
}