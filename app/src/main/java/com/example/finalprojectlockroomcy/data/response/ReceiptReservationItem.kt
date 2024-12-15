package com.example.finalprojectlockroomcy.data.response

import com.google.gson.annotations.SerializedName

data class ReceiptReservationItem(

    @SerializedName("username")
    val username: String,

    @SerializedName("room_type")
    val roomType: String,

    @SerializedName("session")
    val session: String,

    @SerializedName("date")
    val date: String

)
