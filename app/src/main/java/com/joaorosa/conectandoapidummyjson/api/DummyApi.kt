package com.joaorosa.conectandoapidummyjson.api

import com.joaorosa.conectandoapidummyjson.model.DummyImages
import com.joaorosa.conectandoapidummyjson.model.ProductResponse
import retrofit2.Response
import retrofit2.http.GET

interface DummyApi {
    @GET("products?limit=20")
    suspend fun recoveryImagesDummy() : Response<ProductResponse>
}