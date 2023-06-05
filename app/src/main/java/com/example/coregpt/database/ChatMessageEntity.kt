package com.example.coregpt.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("Chat_Message")
data class ChatMessage(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("Id")
    val id: Long =0,

    @ColumnInfo("Request")
    val request : String,

    @ColumnInfo("Response")
    val response: String

)