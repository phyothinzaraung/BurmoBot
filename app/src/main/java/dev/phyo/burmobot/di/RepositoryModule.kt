package dev.phyo.burmobot.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.phyo.burmobot.data.local.dao.DictionaryDao
import dev.phyo.burmobot.data.local.dao.FavouriteDao
import dev.phyo.burmobot.data.local.dao.RecentDao
import dev.phyo.burmobot.data.repository.DictionaryRepositoryImpl
import dev.phyo.burmobot.data.repository.FavouriteRepositoryImpl
import dev.phyo.burmobot.data.repository.RecentRepositoryImpl
import dev.phyo.burmobot.domain.repository.IDictionaryRepository
import dev.phyo.burmobot.domain.repository.IFavouriteRepository
import dev.phyo.burmobot.domain.repository.IRecentRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesDictionaryRepository(
        @ApplicationContext context: Context,
        dictionaryDao: DictionaryDao
    ): IDictionaryRepository = DictionaryRepositoryImpl(context, dictionaryDao)


    @Provides
    @Singleton
    fun providesFavouriteRepository(
        favouriteDao: FavouriteDao
    ): IFavouriteRepository = FavouriteRepositoryImpl(favouriteDao)

    @Provides
    @Singleton
    fun providesRecentRepository(
        recentDao: RecentDao
    ): IRecentRepository = RecentRepositoryImpl(recentDao)
}