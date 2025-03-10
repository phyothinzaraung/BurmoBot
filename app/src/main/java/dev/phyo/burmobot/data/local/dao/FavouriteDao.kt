package dev.phyo.burmobot.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.phyo.burmobot.data.model.FavouriteEntry

@Dao
interface FavouriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavourite(favouriteEntry: FavouriteEntry)

    @Delete
    suspend fun deleteFavourite(favouriteEntry: FavouriteEntry)

    @Query("SELECT * FROM favourite_entries")
    suspend fun getFavouriteEntries(): List<FavouriteEntry>

    @Query("DELETE FROM favourite_entries")
    suspend fun clearFavoriteEntries()
}