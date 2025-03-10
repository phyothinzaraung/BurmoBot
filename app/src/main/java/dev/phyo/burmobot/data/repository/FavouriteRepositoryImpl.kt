package dev.phyo.burmobot.data.repository

import dev.phyo.burmobot.data.local.dao.FavouriteDao
import dev.phyo.burmobot.data.model.FavouriteEntry
import dev.phyo.burmobot.domain.repository.IFavouriteRepository

class FavouriteRepositoryImpl(
    private val favouriteDao: FavouriteDao
): IFavouriteRepository {
    override suspend fun insertFavourite(favouriteEntry: FavouriteEntry) {
        favouriteDao.insertFavourite(favouriteEntry)
    }

    override suspend fun deleteFavourite(favouriteEntry: FavouriteEntry) {
        favouriteDao.deleteFavourite(favouriteEntry)
    }

    override suspend fun getFavourites(): List<FavouriteEntry> {
        return favouriteDao.getFavouriteEntries()
    }
}