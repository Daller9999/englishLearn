package com.sunplacestudio.englishlearn.database.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "WordsTable")
data class WordEntity(
    val word: String,
    val translate: String,
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null
)