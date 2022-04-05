package com.sunplacestudio.englishlearn.fragments.learn

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sunplacestudio.englishlearn.LOG_TAG
import com.sunplacestudio.englishlearn.database.repository.Word
import com.sunplacestudio.englishlearn.database.repository.WordRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.random.Random

class LearnViewModel(
    application: Application,
    private val wordRepository: WordRepository
) : AndroidViewModel(application) {

    private val scopeIO = CoroutineScope(Dispatchers.IO)

    private val _word = MutableLiveData<WordState>()
    val word: LiveData<WordState> = _word

    private val _size = MutableLiveData<Int>()
    val size: LiveData<Int> = _size

    private val _currentSize = MutableLiveData<Int>()
    val currentSize: LiveData<Int> = _currentSize

    private val list = arrayListOf<Word>()

    private var sizeArray = 0
    private var arrayList = arrayListOf<Int>()
    private var min: Int = 0
    private var max: Int = 0

    init {
        scopeIO.launch {
            list.addAll(wordRepository.getWordsList())
            start(0, list.size)
            _size.postValue(list.size)
            min = 0
            max = list.size
        }
    }

    fun next() {
        if (arrayList.isEmpty()) {
            start(min, max)
        }
        val i = Random.nextInt(sizeArray)
        val word = list[arrayList[i]]
        val bool = true//Random.nextBoolean()

        sizeArray--
        _currentSize.postValue(sizeArray)
        arrayList.removeAt(i)

        _word.postValue(WordState(word, bool, !bool))
    }

    fun start(min: Int, max: Int) {
        arrayList.clear()
        for (i in min until max) {
            arrayList.add(i)
        }
        sizeArray = arrayList.size
        this.min = min
        this.max = max
        Log.i(LOG_TAG, arrayList.toString() + "\n" + sizeArray.toString())
    }

}