package com.example.omdb.network

import com.example.films.Retrofit.OMBdiApiService
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

private const val BASE_URL = "https://www.omdbapi.com/"
object RetrofitProvider{

    val apiService: OMBdiApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(OMBdiApiService::class.java)
    }
}