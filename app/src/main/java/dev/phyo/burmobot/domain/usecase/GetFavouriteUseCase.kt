package dev.phyo.burmobot.domain.usecase

import dev.phyo.burmobot.domain.repository.IFavouriteRepository
import javax.inject.Inject

class GetFavouriteUseCase @Inject constructor(private val favouriteRepository: IFavouriteRepository) {
    suspend fun execute() = favouriteRepository.getFavourites()
}