package dev.phyo.burmobot.presentation.chatboscreen.ui

import android.speech.tts.TextToSpeech
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import dev.phyo.burmobot.R
import dev.phyo.burmobot.presentation.util.Screen
import dev.phyo.burmobot.presentation.chatboscreen.viewmodel.ChatBotViewModel
import dev.phyo.burmobot.presentation.util.ToolbarTitle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatbotScreen(viewModel: ChatBotViewModel, navController: NavHostController, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    var userInput by remember { mutableStateOf("") }
    var chatHistory by remember { mutableStateOf(listOf<Pair<String, String>>()) }
    val tts = rememberTextToSpeech(context)
    val dictionary by viewModel.dictionaryEntries.collectAsState()
    var menuExpanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row {
                        Icon(
                            painter = painterResource(R.drawable.bot),
                            contentDescription = "App Icon",
                            modifier = modifier.size(32.dp)
                                .padding(end = 8.dp),
                            tint = Color(0xFF6200EE)
                        )
                        ToolbarTitle("Burmo Bot")
                    }
                },
                actions = {
                    IconButton(onClick = { menuExpanded = true }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu",
                            tint = Color(0xFF6200EE)
                        )
                    }
                    DropdownMenu(
                        expanded = menuExpanded,
                        onDismissRequest = { menuExpanded = false },
                        modifier = modifier.background(Color.White)
                    ) {
                        DropdownMenuItem(text = { Text("Favorites") }, onClick = {
                            menuExpanded = false
                            navController.navigate(Screen.FavouritesScreen.route)
                        })
                        DropdownMenuItem(text = { Text("Recent") }, onClick = {
                            menuExpanded = false
                            navController.navigate(Screen.RecentScreen.route)
                        })
                        DropdownMenuItem(text = { Text("About") }, onClick = {
                            menuExpanded = false
                            navController.navigate(Screen.AboutScreen.route)
                        })
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(modifier = modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(16.dp)
            .background(color = Color(0xFFF5F5F5))
        ) {
            Column(modifier = modifier
                .weight(1f)
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(8.dp))
                .padding(8.dp)
                .verticalScroll(rememberScrollState())
            ) {
                chatHistory.forEach { (input, output) ->
                    ChatMessage(input, output, isFavorite = false,
                        onFavoriteClick = {},
                        onSpeak = {
                            tts?.speak(input, TextToSpeech.QUEUE_FLUSH, null, null)
                        })
                }
            }

            Spacer(modifier = modifier.height(8.dp))

            OutlinedTextField(
                value = userInput,
                onValueChange = { userInput = it },
                placeholder = { Text("Type a word...") },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Send),
                keyboardActions = KeyboardActions(onSend = {
                    if (userInput.isNotBlank()) {
                        val translation = viewModel.translateToMyanmar(userInput, dictionary)
                        chatHistory = chatHistory + (userInput to translation)
                        userInput = ""
                        keyboardController?.hide()
                    }
                }),
                modifier = modifier
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
                            val translation = viewModel.translateToMyanmar(userInput, dictionary)
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
}
