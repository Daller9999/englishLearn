package com.sunplacestudio.englishlearn.fragments.searchword

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sunplacestudio.englishlearn.database.repository.Word
import com.sunplacestudio.englishlearn.database.repository.WordRepository
import com.sunplacestudio.englishlearn.database.room.WordDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SearchViewModel(
    application: Application,
    private val wordRepository: WordRepository
) : AndroidViewModel(application) {

    private val scopeIO = CoroutineScope(Dispatchers.IO)

    private val _words = MutableLiveData<List<Word>>()
    val words: LiveData<List<Word>> = _words

    private val list = arrayListOf<Word>()
    private var job: Job? = null

    init {
        scopeIO.launch {
            list.addAll(wordRepository.getWordsList())
        }
    }

    fun search(string: String) {
        job?.cancel()
        job = scopeIO.launch {
            val resultList = arrayListOf<Word>()
            list.forEach { word ->
                if (word.word.contains(string) || word.translate.contains(string)) {
                    resultList.add(word)
                }
            }
            _words.postValue(resultList)
        }

    }

}