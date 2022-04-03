package com.sunplacestudio.englishlearn.di

import com.sunplacestudio.englishlearn.database.DataBase
import com.sunplacestudio.englishlearn.database.repository.WordRepository
import com.sunplacestudio.englishlearn.database.room.WordDao
import com.sunplacestudio.englishlearn.fragments.addword.AddWordViewModel
import com.sunplacestudio.englishlearn.fragments.editword.EditWordViewModel
import com.sunplacestudio.englishlearn.fragments.etc.EtcViewModel
import com.sunplacestudio.englishlearn.fragments.learn.LearnViewModel
import com.sunplacestudio.englishlearn.fragments.searchword.SearchViewModel
import com.sunplacestudio.englishlearn.fragments.words.WordsViewModel
import org.koin.dsl.module

val modelsFactory = module {
    factory { AddWordViewModel(get(), get()) }
    factory { EtcViewModel(get(), get()) }
    factory { LearnViewModel(get(), get()) }
    factory { SearchViewModel(get(), get()) }
    factory { WordsViewModel(get(), get()) }
    factory { EditWordViewModel(get(), get()) }
}

val dataBaseFactory = module {
    single { DataBase.buildDataBase(get()) }

    fun provideWordDao(dbMain: DataBase): WordDao {
        return dbMain.getWordDao()
    }

    single { provideWordDao(get()) }

    single { WordRepository(get()) }
}