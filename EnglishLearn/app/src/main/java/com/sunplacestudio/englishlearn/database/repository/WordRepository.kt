package com.sunplacestudio.englishlearn.database.repository

import com.sunplacestudio.englishlearn.database.room.WordDao
import com.sunplacestudio.englishlearn.database.room.WordEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WordRepository(
    private val wordDao: WordDao
) {
    suspend fun getWordsList(): List<Word> = withContext(Dispatchers.IO) {
        val list = arrayListOf<Word>()
        wordDao.getWords().forEach {
            list.add(it.toWord())
        }
        return@withContext list
    }

    suspend fun addWord(word: Word) = withContext(Dispatchers.IO) {
        wordDao.addWord(WordEntity(word.word, word.translate))
    }

    suspend fun updateWord(word: Word) = withContext(Dispatchers.IO) {
        wordDao.updateWord(WordEntity(word.word, word.translate, word.id))
    }

    private fun WordEntity.toWord(): Word {
        return Word(word, translate, id ?: -1)
    }

    private fun Word.toWordEntity(): WordEntity {
        return WordEntity(word, translate)
    }

}