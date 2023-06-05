package com.example.coregpt.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CoreGPTDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChat(message: ChatMessage)

    @Query("SELECT * FROM chat_message ORDER BY id DESC")
    fun getAllMessages(): Flow<List<ChatMessage>>
}