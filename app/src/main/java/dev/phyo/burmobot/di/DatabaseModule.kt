package dev.phyo.burmobot.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.phyo.burmobot.data.local.dao.DictionaryDao
import dev.phyo.burmobot.data.local.database.DictionaryDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context): DictionaryDatabase{
        return Room.databaseBuilder(
            context,
            DictionaryDatabase::class.java,
            "dictionary.db"
        ).build()
    }

    @Provides
    fun providesDictionaryDao(database: DictionaryDatabase): DictionaryDao{
        return database.dictionaryDao()
    }
}