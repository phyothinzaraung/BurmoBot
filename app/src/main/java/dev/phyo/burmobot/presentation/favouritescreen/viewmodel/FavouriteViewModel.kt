package dev.phyo.burmobot.presentation.favouritescreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.phyo.burmobot.data.model.FavouriteEntry
import dev.phyo.burmobot.domain.usecase.DeleteFavouriteUseCase
import dev.phyo.burmobot.domain.usecase.GetFavouriteUseCase
import dev.phyo.burmobot.domain.usecase.InsertFavouriteUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(
    private val insertFavouriteUseCase: InsertFavouriteUseCase,
    private val deleteFavouriteUseCase: DeleteFavouriteUseCase,
    private val getFavouriteUseCase: GetFavouriteUseCase
): ViewModel() {

    fun insertFavourite(favouriteEntry: FavouriteEntry){
        viewModelScope.launch {
            insertFavouriteUseCase.execute(favouriteEntry)
        }
    }

    fun deleteFavourite(favouriteEntry: FavouriteEntry){
        viewModelScope.launch {
            deleteFavouriteUseCase.execute(favouriteEntry)
        }
    }

    private val _favourites = MutableStateFlow<List<FavouriteEntry>>(emptyList())
    val favourites: StateFlow<List<FavouriteEntry>>
        get() = _favourites

    fun getFavourites(){
        viewModelScope.launch {
            _favourites.value = getFavouriteUseCase.execute()
        }
    }

}