package com.joaorosa.conectandoapidummyjson.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    const val BASE_URL = "https://dummyjson.com/"

    val retrofit = Retrofit.Builder()
        .baseUrl(RetrofitService.BASE_URL)
        .addConverterFactory( GsonConverterFactory.create() )
        .build()

    val ApiDummy = retrofit.create(DummyAPI::class.java)

}