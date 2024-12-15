package com.example.finalprojectlockroomcy.network

import com.example.finalprojectlockroomcy.data.request.ReceiptReservationRequest
import com.example.finalprojectlockroomcy.data.response.GeneralResponse
import com.example.finalprojectlockroomcy.data.response.ReceiptReservationItem
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @GET("<receipt-lockroom>")
    fun getAllReceipt(): Call<List<ReceiptReservationItem>>

    @POST("<receipt-lockroom>")
    fun createReceipt(@Body request: ReceiptReservationRequest): Call<GeneralResponse>

}