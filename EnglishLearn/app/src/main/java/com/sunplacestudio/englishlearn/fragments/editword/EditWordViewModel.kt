package com.sunplacestudio.englishlearn.fragments.editword

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sunplacestudio.englishlearn.database.repository.Word
import com.sunplacestudio.englishlearn.database.repository.WordRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditWordViewModel(
    application: Application,
    private val wordRepository: WordRepository
) : AndroidViewModel(application) {

    private var id: Long = -1
    private val scopeIO = CoroutineScope(Dispatchers.IO)

    private val _word = MutableLiveData<Word>()
    val word: LiveData<Word> = _word

    fun uploadWord(id: Long) {
        this.id = id
        scopeIO.launch {
            wordRepository.getWordsList().find { it.id == id }?.let {
                _word.postValue(it)
            }
        }
    }

    fun updateWord(word: Word) {
        scopeIO.launch {
            wordRepository.updateWord(Word(word.word, word.translate, id))
        }
    }
}