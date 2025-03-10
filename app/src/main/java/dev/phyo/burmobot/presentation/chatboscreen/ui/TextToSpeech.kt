package dev.phyo.burmobot.presentation.chatboscreen.ui

import android.content.Context
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import java.util.Locale

@Composable
fun rememberTextToSpeech(context: Context): TextToSpeech? {
    var tts by remember { mutableStateOf<TextToSpeech?>(null) }

    LaunchedEffect(context) {
        val ttsEngine = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                Locale("my") // Myanmar Language
            } else {
                Log.e("TTS", "Initialization failed")
            }
        }
        tts = ttsEngine
    }

    DisposableEffect(Unit) {
        onDispose {
            tts?.shutdown() // Proper cleanup
        }
    }

    return tts
}
