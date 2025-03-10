package dev.phyo.burmobot.presentation.chatboscreen.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.phyo.burmobot.data.model.RecentEntry
import dev.phyo.burmobot.presentation.recentscreen.viewmodel.RecentViewModel

@Composable
fun ChatMessage(
    recentViewModel: RecentViewModel,
    input: String,
    output: String,
    isFavorite: Boolean,
    onFavoriteClick: (Boolean, String) -> Unit,
    onSpeak:() -> Unit,
    modifier: Modifier = Modifier
) {
    var favorite by remember { mutableStateOf(isFavorite) }

    LaunchedEffect(key1 = Unit) {
        addRecent(input, recentViewModel)
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth(0.8f)
                .padding(4.dp)
                .background(Color(0xFFE0E0E0), shape = RoundedCornerShape(8.dp))
                .padding(8.dp)
        ) {
            Text(text = input, color = Color.Black)
        }

        Spacer(modifier = modifier.height(4.dp))

        Box(
            modifier = modifier
                .fillMaxWidth(0.8f)
                .align(Alignment.End)
                .padding(4.dp)
                .background(Color(0xFF6200EE), shape = RoundedCornerShape(8.dp))
                .padding(4.dp)
        ) {
            Column(
                modifier = modifier.fillMaxWidth()
            ) {
                if (output != "Translation Not Found"){
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = input,
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = modifier.weight(1f).padding(16.dp)
                        )
                        Row {
                            IconButton(onClick = { onSpeak() }) {
                                Icon(
                                    imageVector = Icons.Default.PlayArrow,
                                    contentDescription = "Speak",
                                    tint = Color.White
                                )
                            }
                            IconButton(onClick = {
                                favorite = !favorite
                                onFavoriteClick(favorite, input )
                            }) {
                                Icon(
                                    imageVector = if (favorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                                    contentDescription = "Favorite",
                                    tint = if (favorite) Color.Red else Color.White
                                )
                            }
                        }
                    }
                }
                HtmlTextView(
                    htmlContent = output,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }
        }
    }
}

fun addRecent(input: String, recentViewModel: RecentViewModel){
    val recent = RecentEntry(
        word = input
    )
    recentViewModel.insertRecent(recent)
}