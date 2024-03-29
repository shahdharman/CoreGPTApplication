package com.example.coregpt.di

import android.content.Context
import com.example.coregpt.network.OpenAIApi
import com.example.coregpt.repository.DatabaseRepository
import com.example.coregpt.repository.NetworkRepository
import com.example.coregpt.util.Constants
import com.example.coregpt.viewmodel.CoreGPTViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule
{
    @Provides
    @Singleton
    fun provideApiServices(): OpenAIApi{
        return Retrofit.Builder()
            .baseUrl("${Constants.BASE_URL}")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OpenAIApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(openAIApi: OpenAIApi): NetworkRepository{
        return NetworkRepository(openAIApi)
    }

    @Provides
    fun provideChatViewModel(
        networkRepository: NetworkRepository,
        databaseRepository: DatabaseRepository,
        @ApplicationContext context: Context
    ): CoreGPTViewModel {
        return CoreGPTViewModel(networkRepository,databaseRepository, context)
    }

}