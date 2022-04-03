package com.sunplacestudio.englishlearn.fragments.learn

import com.sunplacestudio.englishlearn.database.repository.Word

data class WordState(
    val word: Word,
    val isShowWord: Boolean,
    val isShowTranslate: Boolean
)
