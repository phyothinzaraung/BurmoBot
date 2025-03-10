package dev.phyo.burmobot.presentation.chatboscreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.phyo.burmobot.data.model.DictionaryEntry
import dev.phyo.burmobot.domain.usecase.GetDictionaryUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatBotViewModel @Inject constructor(
    getDictionaryUseCase: GetDictionaryUseCase
): ViewModel() {

    private val _dictionaryEntries = MutableStateFlow<List<DictionaryEntry>>(emptyList())
    val dictionaryEntries: StateFlow<List<DictionaryEntry>> get() = _dictionaryEntries

    init {
        viewModelScope.launch {
            _dictionaryEntries.value = getDictionaryUseCase.execute()
        }
    }

    fun translateToMyanmar(text: String, dictionary: List<DictionaryEntry>): String {
        return dictionary.find { it.word.equals(text, ignoreCase = true) }?.definition ?: "Translation Not Found"
    }
}