package dev.phyo.burmobot.presentation.chatboscreen.ui

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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import dev.phyo.burmobot.data.model.FavouriteEntry
import dev.phyo.burmobot.data.model.RecentEntry
import dev.phyo.burmobot.presentation.chatboscreen.viewmodel.ChatBotViewModel
import dev.phyo.burmobot.presentation.favouritescreen.viewmodel.FavouriteViewModel
import dev.phyo.burmobot.presentation.recentscreen.viewmodel.RecentViewModel
import dev.phyo.burmobot.ui.theme.PrimaryColor

@Composable
fun ChatbotScreen(
    chatBotViewModel: ChatBotViewModel,
    favouriteViewModel: FavouriteViewModel,
    recentViewModel: RecentViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier)
{
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    var userInput by remember { mutableStateOf("") }
    var chatHistory by remember { mutableStateOf(listOf<Pair<String, String>>()) }
    val tts = rememberTextToSpeech(context)
    val dictionary by chatBotViewModel.dictionaryEntries.collectAsState()
    val favouriteEntry by chatBotViewModel.dictionaryEntry.collectAsStateWithLifecycle()
    val recentEntries by recentViewModel.recentEntries.collectAsStateWithLifecycle()

    LaunchedEffect(recentEntries) {
        chatHistory = recentEntries.mapNotNull { entry ->
            entry.word?.let { word ->
                entry.definition?.let { definition ->
                    word to definition
                }
            }
        }
    }

    fun isEntryDuplicate(input: String, output: String): Boolean{
        return recentEntries.any { it.word == input && it.definition == output }
    }

    fun sendWordAction(){
        if (userInput.isNotBlank()){
            val normalizedInput = userInput.trim().replace("\\s+".toRegex(), " ")
            val translation = chatBotViewModel.translateToMyanmar(normalizedInput, dictionary)
            if (!isEntryDuplicate(userInput, translation)){
                chatHistory = chatHistory + (userInput to translation)
                addRecent(userInput, translation, recentViewModel)
            }
            userInput = ""
            keyboardController?.hide()
        }
    }

    Scaffold(
        topBar = {
            PrimaryTopAppBar(navController)
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
                    ChatMessage(recentViewModel, input, output, isFavorite = false,
                        onFavoriteClick = { isFav, favouriteWord ->
                            chatBotViewModel.getDictionaryByWord(word = favouriteWord)

                            val entry = favouriteEntry?.let {
                                FavouriteEntry(
                                    it.id,
                                    it.word,
                                    it.stripWord
                                )
                            }
                            entry?.let {
                                if (isFav){
                                    favouriteViewModel.insertFavourite(entry)
                                }else{
                                    favouriteViewModel.deleteFavourite(entry)
                                }
                            }
                        },
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
                    sendWordAction()
                }),
                modifier = modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .background(Color.White, shape = RoundedCornerShape(24.dp)),
                shape = RoundedCornerShape(24.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PrimaryColor,
                    unfocusedLabelColor = Color.Gray,
                    cursorColor = PrimaryColor,
                    focusedTextColor = Color.Black,
                    unfocusedBorderColor = Color.Black
                ),
                trailingIcon = {
                    IconButton(onClick = {
                        sendWordAction()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Send,
                            contentDescription = "send",
                            tint = PrimaryColor
                        )
                    }
                }
            )
        }
    }
}

private fun addRecent(input: String, output: String, recentViewModel: RecentViewModel){
    val recent = RecentEntry(
        word = input,
        definition = output
    )
    recentViewModel.insertRecent(recent)
}
