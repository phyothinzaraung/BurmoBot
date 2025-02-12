package dev.phyo.burmobot.presentation.ui

import android.speech.tts.TextToSpeech
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.phyo.burmobot.data.model.DictionaryEntry
import dev.phyo.burmobot.presentation.viewmodel.MainViewModel

@Composable
fun ChatbotScreen(viewModel: MainViewModel) {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    var userInput by remember { mutableStateOf("") }
    var chatHistory by remember { mutableStateOf(listOf<Pair<String, String>>()) }
    val tts = rememberTextToSpeech(context)
    val dictionary by viewModel.dictionaryEntries.collectAsState()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
        .background(color = Color(0xFFF5F5F5))
    ) {
        Text("Burmo Bot",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF6200EE),
            modifier = Modifier
                .padding(bottom = 8.dp)
                .align(Alignment.CenterHorizontally)
        )

        Column(modifier = Modifier
            .weight(1f)
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .padding(8.dp)
            .verticalScroll(rememberScrollState())
        ) {
            chatHistory.forEach { (input, output) ->
                ChatMessage(input, output) {
                    tts?.speak(output, TextToSpeech.QUEUE_FLUSH, null, null)
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = userInput,
            onValueChange = { userInput = it },
            placeholder = { Text("Type a message...") },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Send),
            keyboardActions = KeyboardActions(onSend = {
                if (userInput.isNotBlank()) {
                    val translation = translateToMyanmar(userInput, dictionary)
                    chatHistory = chatHistory + (userInput to translation)
                    userInput = ""
                    keyboardController?.hide()
                }
            }),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .background(Color.White, shape = RoundedCornerShape(24.dp)),
            shape = RoundedCornerShape(24.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF6200EE),
                unfocusedLabelColor = Color.Gray,
                cursorColor = Color(0xFF6200EE),
                focusedTextColor = Color.Black,
                unfocusedBorderColor = Color.Black
            ),
            trailingIcon = {
                IconButton(onClick = {
                    if (userInput.isNotBlank()){
                        val translation = translateToMyanmar(userInput, dictionary)
                        chatHistory = chatHistory + (userInput to translation)
                        userInput = ""
                        keyboardController?.hide()
                    }
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Send,
                        contentDescription = "send",
                        tint = Color(0xFF6200EE)
                    )
                }
            }
        )
    }
}

fun translateToMyanmar(text: String, dictionary: List<DictionaryEntry>): String {
    return dictionary.find { it.word.equals(text, ignoreCase = true) }?.definition ?: "Translation Not Found"
}