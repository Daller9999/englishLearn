package com.sunplacestudio.englishlearn.fragments.addword

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.sunplacestudio.englishlearn.database.repository.Word
import com.sunplacestudio.englishlearn.database.repository.WordRepository
import com.sunplacestudio.englishlearn.database.room.WordDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddWordViewModel(
    application: Application,
    private val wordRepository: WordRepository
) : AndroidViewModel(application) {

    private val scopeIO = CoroutineScope(Dispatchers.IO)

    fun addWord(word: Word) {
        scopeIO.launch {
            wordRepository.addWord(word)
        }
    }

}