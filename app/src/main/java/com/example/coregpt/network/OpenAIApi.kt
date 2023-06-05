package com.example.coregpt.network

import com.example.coregpt.models.networkModel.OpenAIRequestBody
import com.example.coregpt.models.networkModel.OpenAIResponse
import com.example.coregpt.util.Constants
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface OpenAIApi {
    @Headers("Content-Type: application/json", "Authorization: Bearer ${Constants.API_KEY}")
    @POST("${Constants.END_POINT}")
    suspend fun generateResponse(@Body requestBody: OpenAIRequestBody): OpenAIResponse
}