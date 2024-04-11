package com.example.musicapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiInterface {
    @Headers(
        "X-RapidAPI-Key:3ff432f29cmsh5e830ed9d2910e9p1c916ajsn0d5956606963",
        "X-RapidAPI-Host:deezerdevs-deezer.p.rapidapi.com"
    )
    @GET("search")

    fun getData(@Query("q") query: String): Call<MyData>

}