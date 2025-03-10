package dev.phyo.burmobot.domain.repository

import dev.phyo.burmobot.data.model.FavouriteEntry

interface IFavouriteRepository {
    suspend fun insertFavourite(favouriteEntry: FavouriteEntry)

    suspend fun deleteFavourite(favouriteEntry: FavouriteEntry)

    suspend fun getFavourites(): List<FavouriteEntry>
}