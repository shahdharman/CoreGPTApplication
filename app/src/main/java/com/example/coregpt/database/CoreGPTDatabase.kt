package com.example.coregpt.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ChatMessage::class, NoteEntity::class], version = 1)
abstract class CoreGPTDatabase: RoomDatabase()
{
    abstract fun CoreGPTDao(): CoreGPTDao
}