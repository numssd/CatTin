package com.fakhrulasa.realmdb

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface CatService {
    @Headers("x-api-key: c14f9e73-2254-44d9-8824-9accee0155d4")
    @GET("images/search?mime_types=gif&limit=10")
    fun getImage(): Call<List<Cat>>
}