package dev.phyo.burmobot.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ChatMessage(input: String, output: String, onSpeak: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(4.dp)
                .background(Color(0xFFE0E0E0), shape = RoundedCornerShape(8.dp))
                .padding(8.dp)
        ) {
            Text(text = input, color = Color.Black)
        }

        Spacer(modifier = Modifier.height(4.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .align(Alignment.End)
                .padding(4.dp)
                .background(Color(0xFF6200EE), shape = RoundedCornerShape(8.dp))
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            HtmlTextView(
                htmlContent = output,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            IconButton(
                onClick = {onSpeak()},
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = "Speak",
                    tint = Color.White
                )
            }
        }
    }
}