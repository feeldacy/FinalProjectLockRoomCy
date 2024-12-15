package com.example.finalprojectlockroomcy.data.response

import com.google.gson.annotations.SerializedName

data class GeneralResponse(
    @SerializedName("success")
    val success: Boolean,

    @SerializedName("message")
    val message: String,

    @SerializedName("id")
    val id: Int?
)
