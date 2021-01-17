package com.newton.eventgo.models

import com.newton.eventgo.models.dto.EventDetailRequest
import java.math.BigDecimal
import java.util.*

class EventDetail(
    val id: Long? = 0,
    val image: String? = "",
    val title: String? = "",
    val date: Long? = 0,
    val long: Double? = 0.0,
    val lat: Double? = 0.0,
    val description: String? = "",
    val price: BigDecimal? = BigDecimal.ZERO
) {
    fun eventDetailRequestToEventDetail(eventDetailRequest: EventDetailRequest)
    = EventDetail(
        eventDetailRequest.id?.toLong(),
        eventDetailRequest.image,
        eventDetailRequest.title,
        eventDetailRequest.date,
        eventDetailRequest.longitude,
        eventDetailRequest.latitude,
        eventDetailRequest.description,
        eventDetailRequest.price
    )
}