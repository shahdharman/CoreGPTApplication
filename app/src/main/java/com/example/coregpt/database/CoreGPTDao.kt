package com.example.coregpt.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CoreGPTDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChat(message: ChatMessage)

    @Query("SELECT * FROM chat_message ORDER BY id DESC")
    fun getAllMessages(): Flow<List<ChatMessage>>

    @Delete
    suspend fun deleteChat(chatMessage: ChatMessage)

    @Query("DELETE FROM chat_message")
    suspend fun deleteAllChatMessage()



    //for My Note
    @Query("SELECT * FROM mynote_tbl WHERE category = :category")
    fun getMyNoteByCategory(category: String) : Flow<List<MyNote>>

    //Insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(myNote: MyNote)

    //Update
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(myNote: MyNote)

    //deleteAll
    @Query("DELETE FROM mynote_tbl")
    suspend fun deleteAllMyNote()

    //Delete
    @Delete
    suspend fun deleteNote(myNote: MyNote)



}