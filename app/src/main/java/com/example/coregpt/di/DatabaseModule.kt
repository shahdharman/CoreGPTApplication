package com.example.coregpt.di

import android.content.Context
import androidx.room.Room
import com.example.coregpt.database.CoreGPTDao
import com.example.coregpt.database.CoreGPTDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideChatDao(coreGPTDatabase: CoreGPTDatabase): CoreGPTDao {
        return coreGPTDatabase.CoreGPTDao()
    }

    @Provides
    @Singleton
    fun provideCoreGPTDatabase(@ApplicationContext context: Context): CoreGPTDatabase {
        return Room.databaseBuilder(
            context = context,
            CoreGPTDatabase::class.java,
            "CoreGPT_DB"
        ).fallbackToDestructiveMigration()
            .build()
    }
}