package com.example.finalprojectlockroomcy.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HistoryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(historyItem: HistoryItem)

    @Query("SELECT * FROM history_receipt")
    suspend fun getAllHistory(): List<HistoryItem>

}