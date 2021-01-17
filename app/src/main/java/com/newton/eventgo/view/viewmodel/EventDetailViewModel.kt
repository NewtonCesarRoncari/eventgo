package com.newton.eventgo.view.viewmodel

import androidx.lifecycle.ViewModel
import com.newton.eventgo.repository.EventRepository

class EventDetailViewModel(
    eventId: Long,
    private val repository: EventRepository
) : ViewModel() {

    fun findEventById(eventId: Long) = repository.findEventById(eventId)

    fun checkEventReturned() = repository.eventReturned
}