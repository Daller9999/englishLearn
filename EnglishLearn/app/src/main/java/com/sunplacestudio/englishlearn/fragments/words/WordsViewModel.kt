package com.sunplacestudio.englishlearn.fragments.words

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sunplacestudio.englishlearn.database.repository.Word
import com.sunplacestudio.englishlearn.database.repository.WordRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WordsViewModel(
    application: Application,
    private val wordRepository: WordRepository
) : AndroidViewModel(application) {

    private val scopeIO = CoroutineScope(Dispatchers.IO)

    private val _words = MutableLiveData<List<Word>>()
    val words: LiveData<List<Word>> = _words

    fun update() {
        scopeIO.launch {
            if (_words.value?.isNotEmpty() == true) {
                _words.postValue(_words.value)
            } else {
                _words.postValue(wordRepository.getWordsList())
            }
        }
    }
}