package com.example.coregpt.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("Note_Entity")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("Id")
    val id : Long = 0,

    @ColumnInfo("Category")
    val category : String,

    @ColumnInfo("Question")
    val question: String,
    @ColumnInfo("Answer")
    val answer: String
)
