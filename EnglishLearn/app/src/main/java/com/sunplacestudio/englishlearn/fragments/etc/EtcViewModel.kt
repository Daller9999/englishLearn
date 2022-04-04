package com.sunplacestudio.englishlearn.fragments.etc

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.sunplacestudio.englishlearn.LOG_TAG
import com.sunplacestudio.englishlearn.database.DataBase
import com.sunplacestudio.englishlearn.database.repository.Word
import com.sunplacestudio.englishlearn.database.repository.WordRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EtcViewModel(
    application: Application,
    private val wordRepository: WordRepository,
    private val dataBase: DataBase
) : AndroidViewModel(application) {

    private val scopeIO = CoroutineScope(Dispatchers.IO)

    private val _state = MutableLiveData<EtcState>()
    val state: LiveData<EtcState> = _state

    private val list = arrayListOf<Word>()

    init {
        scopeIO.launch {
            list.addAll(wordRepository.getWordsList())
            _state.postValue(EtcState(list.size))
        }
    }

    fun printAllToConsole() {
        var maxLen = 0
        list.forEach {
            if (it.word.length > maxLen) {
                maxLen = it.word.length
            }
        }
        var i = 1
        list.forEach { word ->
            Log.i(LOG_TAG, "$i) ${word.word.checkWordLength(maxLen)}: ${word.translate}")
            i++
        }
    }

    fun closeDB() {
        dataBase.close()
    }

    private fun String.checkWordLength(lengthMax: Int): String {
        return if (length > lengthMax)
            this
        else {
            var string = this
            while (string.length < lengthMax) {
                string += " "
            }
            string
        }
    }

}