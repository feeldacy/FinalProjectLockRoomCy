package com.example.finalprojectlockroomcy.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    fun getInstance(): ApiService {
        // mencatat log request dan response http
        val mHttpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        // mengirim dan menerima data
        val mOkHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(mHttpLoggingInterceptor)
            .build()

        // konfigurasi retrofit
        val builder = Retrofit.Builder()
            .baseUrl("https://ppbo-api-b.vercel.app/vRH1I/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(mOkHttpClient)
            .build()

        // mengembalikan instance apiclient
        return builder.create(ApiService::class.java)
    }

}