package com.sunplacestudio.englishlearn.fragments.learn

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
import kotlin.random.Random

class LearnViewModel(
    application: Application,
    private val wordRepository: WordRepository
) : AndroidViewModel(application) {

    private val scopeIO = CoroutineScope(Dispatchers.IO)

    private val _word = MutableLiveData<WordState>()
    val word: LiveData<WordState> = _word

    private val list = arrayListOf<Word>()

    init {
        scopeIO.launch {
            list.addAll(wordRepository.getWordsList())
        }
    }

    fun next() {
        val word = list[Random.nextInt(list.size)]
        val bool = Random.nextBoolean()
        _word.postValue(WordState(word, bool, !bool))
    }

}