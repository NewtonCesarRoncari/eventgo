package com.newton.eventgo.models

import com.fasterxml.jackson.annotation.JsonIgnore
import com.newton.eventgo.models.dto.EventDetailRequest
import java.math.BigDecimal

class Event(
    val id: Long? = 0,
    val image: String? = "",
    val title: String? = "",
    val date: Long? = 0,
    val price: BigDecimal? = BigDecimal.ZERO
) {
    fun eventsRequestToEvents(eventsRequest: List<EventDetailRequest>): MutableList<Event> {

        val events = mutableListOf<Event>()

        eventsRequest.forEach { eventRequest ->
            events.add(
                Event(
                    eventRequest.id!!.toLong(),
                    eventRequest.image,
                    eventRequest.title,
                    eventRequest.date,
                    eventRequest.price
                )
            )
        }
        return events
    }
}