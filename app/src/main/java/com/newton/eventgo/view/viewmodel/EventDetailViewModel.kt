package com.newton.eventgo.view.viewmodel

import android.widget.Button
import androidx.lifecycle.ViewModel
import com.newton.eventgo.extensions.formatForBrazilianDate
import com.newton.eventgo.extensions.formatForBrazilianHour
import com.newton.eventgo.extensions.fromTimesTamp
import com.newton.eventgo.models.EventDetail
import com.newton.eventgo.models.dto.CheckinRequest
import com.newton.eventgo.repository.EventRepository

class EventDetailViewModel(
    eventId: Long,
    private val repository: EventRepository
) : ViewModel() {

    fun findEventById(
        eventId: Long,
        whenFailure: () -> Unit
    ) = repository.findEventById(eventId, whenFailure)

    fun checkEventReturned() = repository.eventReturned

    fun postCheckin(
        checkinRequest: CheckinRequest,
        whenSuccess: () -> Unit,
        whenFailure: () -> Unit
    ) = repository.postBuyOrder(checkinRequest, whenSuccess, whenFailure)

    fun disableButtonCheckin(button: Button) {
        button.isClickable = false
        button.isFocusable = false
        button.isEnabled = false
    }

    fun shareText(event: EventDetail) =
        "${event.title}\n\n" +
                "${event.description}\n" +
                "${event.date?.fromTimesTamp()?.formatForBrazilianDate() ?: "-"}\n" +
                (event.date?.fromTimesTamp()?.formatForBrazilianHour() ?: "-")
}