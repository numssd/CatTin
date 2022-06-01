package com.fakhrulasa.realmdb

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://api.thecatapi.com/v1/")
        .build()

    fun catService(): CatService = retrofit.create(CatService::class.java)
}