package com.newton.eventgo.repository

import androidx.lifecycle.MutableLiveData
import com.newton.eventgo.extensions.formatForBrazilianDate
import com.newton.eventgo.extensions.formatForBrazilianHour
import com.newton.eventgo.extensions.fromTimesTamp
import com.newton.eventgo.models.EventDetail
import com.newton.eventgo.models.dto.CheckinRequest
import com.newton.eventgo.models.dto.EventDetailRequest
import com.newton.eventgo.retrofit.callback.CallbackWithReturn
import com.newton.eventgo.retrofit.service.EventService

class EventRepository(
    private val service: EventService
) {
    var eventsReturned =
        MutableLiveData<List<EventDetailRequest>>().apply { postValue(null) }
    var eventReturned =
        MutableLiveData<EventDetailRequest>().apply { postValue(null) }

    fun getEvents(whenFailure: () -> Unit = {}) {
        val call = service.getEvents()
        call.enqueue(CallbackWithReturn(
            object : CallbackWithReturn.AnswerCallback<List<EventDetailRequest>> {
                override fun whenSuccess(result: List<EventDetailRequest>, requestCode: Int) {

                    if (requestCode == 200) eventsReturned.value = result else whenFailure()
                }

                override fun whenFailure(error: String) {
                    whenFailure()
                }
            }
        ))
    }

    fun findEventById(
        id: Long,
        whenFailure: () -> Unit = {}
    ) {
        val call = service.findEventById(id)
        call.enqueue(CallbackWithReturn(
            object : CallbackWithReturn.AnswerCallback<EventDetailRequest> {
                override fun whenSuccess(result: EventDetailRequest, requestCode: Int) {

                    if (requestCode == 200) eventReturned.value = result else whenFailure()
                }

                override fun whenFailure(error: String) {
                    whenFailure()
                }
            }
        ))
    }

    fun postBuyOrder(
        checkinRequest: CheckinRequest,
        whenSuccess: () -> Unit = {},
        whenFailure: () -> Unit = {}
    ) {
        val call = service.postCheckin(checkinRequest)
        call.enqueue(CallbackWithReturn(
            object : CallbackWithReturn.AnswerCallback<Any> {
                override fun whenSuccess(result: Any, requestCode: Int) {
                    if (requestCode == 201) whenSuccess() else whenFailure()
                }

                override fun whenFailure(error: String) {
                    whenFailure()
                }
            }
        ))
    }

    fun shareText(event: EventDetail) =
        "${event.title}\n\n" +
                "${event.description}\n" +
                "${event.date?.fromTimesTamp()?.formatForBrazilianDate() ?: "-"}\n" +
                (event.date?.fromTimesTamp()?.formatForBrazilianHour() ?: "-")
}