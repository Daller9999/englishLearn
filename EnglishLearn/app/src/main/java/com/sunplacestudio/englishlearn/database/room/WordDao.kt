package com.sunplacestudio.englishlearn.database.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.sunplacestudio.englishlearn.database.repository.Word

@Dao
interface WordDao {

    @Query("select * from WordsTable")
    fun getWords(): List<WordEntity>

    @Insert
    fun addWord(wordEntity: WordEntity)

    @Update
    fun updateWord(wordEntity: WordEntity)

}