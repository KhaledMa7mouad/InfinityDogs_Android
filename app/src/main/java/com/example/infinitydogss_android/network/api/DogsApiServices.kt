package com.example.infinitydogss_android.network.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
//Singleton Object
object RetrofitClient {
    private const val Base_URL = "https://dog.ceo"
    val apiService: DogAPICallable by lazy {

        Retrofit.Builder()
            .baseUrl(Base_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DogAPICallable::class.java)
    }


}