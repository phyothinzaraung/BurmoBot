package dev.phyo.burmobot.presentation.chatboscreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.phyo.burmobot.data.model.DictionaryEntry
import dev.phyo.burmobot.domain.usecase.GetDictionaryByWordUseCase
import dev.phyo.burmobot.domain.usecase.GetDictionaryUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatBotViewModel @Inject constructor(
    private val getDictionaryUseCase: GetDictionaryUseCase,
    private val getDictionaryByWordUseCase: GetDictionaryByWordUseCase
): ViewModel() {

    private val _dictionaryEntries = MutableStateFlow<List<DictionaryEntry>>(emptyList())
    val dictionaryEntries: StateFlow<List<DictionaryEntry>> get() = _dictionaryEntries

    init {
        viewModelScope.launch {
            _dictionaryEntries.value = getDictionaryUseCase.execute()
        }
    }

    private val _dictionaryEntry = MutableStateFlow<DictionaryEntry?>(null)
    val dictionaryEntry: StateFlow<DictionaryEntry?>
        get() = _dictionaryEntry

    fun getDictionaryByWord(word: String){
        viewModelScope.launch {
            _dictionaryEntry.value = getDictionaryByWordUseCase.execute(word) ?: DictionaryEntry(id = 0, word = word, definition = "Translation Not Found")
        }
    }

    fun translateToMyanmar(text: String, dictionary: List<DictionaryEntry>): String {
        return dictionary.find { it.word.equals(text, ignoreCase = true) }?.definition ?: "Translation Not Found"
    }
}