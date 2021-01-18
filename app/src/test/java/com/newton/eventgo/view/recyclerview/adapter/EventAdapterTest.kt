package com.newton.eventgo.view.recyclerview.adapter

import android.content.Context
import com.newton.eventgo.models.Event
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class EventAdapterTest {

    private val context = Mockito.mock(Context::class.java)

    private val adapter = EventAdapter(
        context, mutableListOf(
            Event(),
            Event()
        )
    )

    @Test
    fun returnSizeOfAdapterWhenHaveTwoEvents() {
        assertEquals(2, adapter.itemCount)
    }
}