package dev.phyo.burmobot.presentation.util

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun ToolbarTitle(toolbarText: String, modifier: Modifier = Modifier) {
    Text(
        text = toolbarText,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF6200EE),
        modifier = modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
    )
}