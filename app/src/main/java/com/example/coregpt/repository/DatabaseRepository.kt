package com.example.coregpt.repository

import com.example.coregpt.database.ChatMessage
import com.example.coregpt.database.CoreGPTDao
import javax.inject.Inject

class DatabaseRepository @Inject constructor(
    private val coreGPTDao: CoreGPTDao
)
{
    suspend fun insertChat(message: ChatMessage) = coreGPTDao.insertChat(message)
}