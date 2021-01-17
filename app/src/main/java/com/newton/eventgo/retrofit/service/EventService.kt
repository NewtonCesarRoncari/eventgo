package com.newton.eventgo.retrofit.service

import com.newton.eventgo.models.dto.EventDetailRequest
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface EventService {

    @GET("events")
    fun getEvents(): Call<List<EventDetailRequest>>

    @GET("events/{id}")
    fun findEventById(@Path("id") id: Long): Call<EventDetailRequest>
}