package dev.phyo.burmobot.domain.usecase

import dev.phyo.burmobot.data.model.FavouriteEntry
import dev.phyo.burmobot.domain.repository.IFavouriteRepository
import javax.inject.Inject

class InsertFavouriteUseCase @Inject constructor(private val favouriteRepository: IFavouriteRepository) {
    suspend fun execute(favouriteEntry: FavouriteEntry) = favouriteRepository.insertFavourite(favouriteEntry)
}