package com.example.zeebooks.commons.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.HTTP
import java.util.concurrent.TimeUnit

object RetrofitClient {

    private const val baseUrl = "https://zeebooks.herokuapp.com/"

    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .callFactory(this.okHttpClient)
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)





}
