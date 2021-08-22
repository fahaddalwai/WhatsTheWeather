package com.example.whatstheweather.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


private const val BASE_URL="https://api.openweathermap.org/"

const val CLIENT_ID="0e1fe54ed3c19a2d9662b114fb3ff045"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


interface ApiService {
    @GET("data/2.5/find")        //which is https://api.openweathermap.org/data/2.5/find?
    suspend fun getResponse(
        @Query("lat") lat: Int,
        @Query("lon") lon: Int,
        @Query("cnt") cnt: Int,
        @Query("appid") appid: String,
    ): Response
}

object ResponseApi {
    val retrofitService : ApiService by lazy {
        retrofit.create(ApiService::class.java) }
}