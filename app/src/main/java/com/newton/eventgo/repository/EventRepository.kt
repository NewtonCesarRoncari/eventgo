package com.newton.eventgo.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
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

    fun getEvents() {
        val call = service.getEvents()
        call.enqueue(CallbackWithReturn(
            object : CallbackWithReturn.AnswerCallback<List<EventDetailRequest>> {
                override fun whenSucess(result: List<EventDetailRequest>) {
                    eventsReturned.value = result
                }

                override fun whenFailure(error: String) {
                    Log.e("retrofit", error)
                }
            }
        ))
    }

    fun findEventById(id: Long) {
        val call = service.findEventById(id)
        call.enqueue(CallbackWithReturn(
            object : CallbackWithReturn.AnswerCallback<EventDetailRequest> {
                override fun whenSucess(result: EventDetailRequest) {
                    eventReturned.value = result
                }

                override fun whenFailure(error: String) {
                    Log.e("retrofit", error)
                }
            }
        ))
    }

    fun postBuyOrder(checkinRequest: CheckinRequest) {
        val call = service.postCheckin(checkinRequest)
        call.enqueue(CallbackWithReturn(
            object: CallbackWithReturn.AnswerCallback<Any>{
                override fun whenSucess(result: Any) {
                    Log.i("retrofit", "request sucess")
                }
                override fun whenFailure(error: String) {
                    Log.e("retrofit", error)
                }
            }
        ))
    }
}