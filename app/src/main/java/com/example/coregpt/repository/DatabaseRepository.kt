package com.example.coregpt.repository

import com.example.coregpt.database.ChatMessage
import com.example.coregpt.database.CoreGPTDao
import com.example.coregpt.database.MyNote
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

    suspend fun deleteAllChat() = coreGPTDao.deleteAllChatMessage()

    fun getAllChat(): Flow<List<ChatMessage>> = coreGPTDao.getAllMessages()
        .flowOn(Dispatchers.IO).conflate()


    //For My Note

    suspend fun insert(myNote: MyNote) = coreGPTDao.insert(myNote)

    suspend fun update(myNote: MyNote) = coreGPTDao.update(myNote)

    suspend fun delete(myNote: MyNote) = coreGPTDao.deleteNote(myNote)

    suspend fun deleteAll() = coreGPTDao.deleteAllMyNote()


    fun getMyNoteByCategory(category: String): Flow<List<MyNote>>
            = coreGPTDao.getMyNoteByCategory(category)
        .flowOn(Dispatchers.IO).conflate()


}