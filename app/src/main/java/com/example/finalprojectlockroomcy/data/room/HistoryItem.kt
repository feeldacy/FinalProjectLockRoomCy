package com.example.finalprojectlockroomcy.data.room

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history_receipt")
data class HistoryItem(

    @PrimaryKey(autoGenerate = true)
    @NonNull
    val id: Int = 0,

    @ColumnInfo(name = "username")
    val username: String,

    @ColumnInfo(name = "room_type")
    val roomType: String,

    @ColumnInfo(name = "date")
    val date: String,

    @ColumnInfo(name = "session")
    val session: String
)


