package com.newton.eventgo.extensions

import org.junit.Assert.*
import org.junit.Test
import java.util.*

class DateExtensionKtTest {

    @Test
    fun formatForBrazilianDate() {
        val dateReturned = Date(1610931559).formatForBrazilianDate()
        assertEquals(dateReturned, "19/01")
    }

    @Test
    fun formatForBrazilianHour() {
        val dateReturned = Date(1610931559).formatForBrazilianHour()
        assertEquals(dateReturned, "12:28")
    }

}
