package com.sunplacestudio.englishlearn.fragments.etc

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.sunplacestudio.englishlearn.database.room.WordDao

class EtcViewModel(
    application: Application,
    wordDao: WordDao
) : AndroidViewModel(application) {

}