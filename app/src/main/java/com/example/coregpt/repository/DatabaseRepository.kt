package com.example.coregpt.repository

import com.example.coregpt.database.ChatMessage
import com.example.coregpt.database.CoreGPTDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DatabaseRepository @Inject constructor(
    private val coreGPTDao: CoreGPTDao
)
{
    suspend fun insertChat(message: ChatMessage) = coreGPTDao.insertChat(message)
    suspend fun deleteChat(message: ChatMessage) = coreGPTDao.deleteChat(message)

    suspend fun deleteAllChat() = coreGPTDao.deleteAll()

    fun getAllChat(): Flow<List<ChatMessage>> = coreGPTDao.getAllMessages()
        .flowOn(Dispatchers.IO).conflate()

}