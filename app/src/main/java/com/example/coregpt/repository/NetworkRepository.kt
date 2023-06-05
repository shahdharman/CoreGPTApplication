package com.example.coregpt.repository

import android.content.Context
import com.example.coregpt.models.networkModel.OpenAIRequestBody
import com.example.coregpt.models.networkModel.OpenAIResponse
import com.example.coregpt.network.OpenAIApi
import com.example.coregpt.viewmodel.CoreGPTViewModel
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

class NetworkRepository @Inject constructor(
    private val openAIApi: OpenAIApi
) {

    suspend fun generateResponse(requestBody: OpenAIRequestBody): OpenAIResponse {
        return openAIApi.generateResponse(requestBody)
    }


}