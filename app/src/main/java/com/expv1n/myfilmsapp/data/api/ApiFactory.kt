package com.expv1n.myfilmsapp.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiFactory {

    private val retrofit: Retrofit by lazy {
        initRetrofit()
    }

    private fun createInterceptor(): HttpLoggingInterceptor  {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level= HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(createInterceptor())
        .build()

    private fun initRetrofit(): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(client)
        .build()

    val apiService = retrofit.create(ApiService::class.java)


    companion object {
        private const val BASE_URL = "https://kinopoiskapiunofficial.tech/"
    }
}