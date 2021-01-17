package com.newton.eventgo.retrofit

import com.newton.eventgo.retrofit.service.EventService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

private const val BASE_URL = "http://5f5a8f24d44d640016169133.mockapi.io/api/"

class ConnectionRetrofit {

    private val client by lazy {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(JacksonConverterFactory.create())
            .client(client)
            .build()
    }

    val eventService: EventService by lazy {
        retrofit.create(EventService::class.java)
    }
}