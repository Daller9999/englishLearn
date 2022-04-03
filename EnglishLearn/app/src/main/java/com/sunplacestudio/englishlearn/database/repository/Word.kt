package com.sunplacestudio.englishlearn.database.repository

data class Word(
    val word: String,
    val translate: String,
    val id: Long? = null
)
