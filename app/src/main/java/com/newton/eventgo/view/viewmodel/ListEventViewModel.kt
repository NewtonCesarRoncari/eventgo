package com.newton.eventgo.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.newton.eventgo.models.dto.EventDetailRequest
import com.newton.eventgo.repository.EventRepository

class ListEventViewModel(
    private val repository: EventRepository
) : ViewModel() {

    fun getEvents() = repository.getEvents()

    fun checkEventsReturned(): LiveData<List<EventDetailRequest>> = repository.eventsReturned
}