package dev.phyo.burmobot.presentation.recentscreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.phyo.burmobot.data.model.RecentEntry
import dev.phyo.burmobot.domain.usecase.ClearAllRecentUseCase
import dev.phyo.burmobot.domain.usecase.GetLatestRecentUseCase
import dev.phyo.burmobot.domain.usecase.GetRecentUseCase
import dev.phyo.burmobot.domain.usecase.InsertRecentUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecentViewModel @Inject constructor(
    private val getRecentUseCase: GetRecentUseCase,
    private val insertRecentUseCase: InsertRecentUseCase,
    private val clearAllRecentUseCase: ClearAllRecentUseCase,
    private val getLatestRecentUseCase: GetLatestRecentUseCase
): ViewModel() {

    fun insertRecent(recentEntry: RecentEntry){
        viewModelScope.launch {
            insertRecentUseCase.execute(recentEntry)
        }
    }

    fun clearAllRecent(){
        viewModelScope.launch {
            clearAllRecentUseCase.execute()
        }
    }

    private val _recentEntries = MutableStateFlow<List<RecentEntry>>(emptyList())
    val recentEntries: StateFlow<List<RecentEntry>>
        get() = _recentEntries

    init {
        viewModelScope.launch {
            _recentEntries.value = getLatestRecentUseCase.execute()
        }
    }
}