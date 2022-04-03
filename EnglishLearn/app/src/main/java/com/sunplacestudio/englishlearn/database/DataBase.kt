package com.sunplacestudio.englishlearn.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sunplacestudio.englishlearn.database.room.WordDao
import com.sunplacestudio.englishlearn.database.room.WordEntity

@Database(entities = [WordEntity::class], version = 1)
abstract class DataBase : RoomDatabase() {
    companion object {
        fun buildDataBase(application: Application): DataBase {
            return Room.databaseBuilder(
                application.applicationContext,
                DataBase::class.java,
                "DataBaseWords"
            ).build()
        }
    }

    abstract fun getWordDao(): WordDao
}