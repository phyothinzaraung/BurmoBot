package dev.phyo.burmobot.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.phyo.burmobot.data.local.dao.DictionaryDao
import dev.phyo.burmobot.data.repository.DictionaryRepositoryImpl
import dev.phyo.burmobot.domain.repository.DictionaryRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesDictionaryRepository(
        @ApplicationContext context: Context,
        dictionaryDao: DictionaryDao
    ): DictionaryRepository{
        return DictionaryRepositoryImpl(context, dictionaryDao)
    }
}