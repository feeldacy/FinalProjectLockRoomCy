package com.example.finalprojectlockroomcy.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase



@Database(entities = [HistoryItem::class], version = 1, exportSchema = false)

abstract class HistoryRoomDatabase : RoomDatabase(){
    abstract fun historyDao() : HistoryDao?

    companion object {
        @Volatile
        private var INSTANCE: HistoryRoomDatabase? = null

        fun getDatabase(context: Context) : HistoryRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(HistoryRoomDatabase::class.java){
                    INSTANCE = databaseBuilder(context.applicationContext,
                        HistoryRoomDatabase::class.java, "history_database").build()
                }
            }
            return INSTANCE
        }
    }

}