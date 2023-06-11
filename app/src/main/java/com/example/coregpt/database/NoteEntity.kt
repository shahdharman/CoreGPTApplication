package com.example.coregpt.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("mynote_tbl")
data class MyNote(
    @PrimaryKey(autoGenerate = true)
    val id: Long =0,

    @ColumnInfo("question")
    var question: String,

    @ColumnInfo("answer")
    var answer: String,

    @ColumnInfo("category")
    var category: String
)
