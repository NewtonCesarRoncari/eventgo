package com.newton.eventgo.models

import java.math.BigDecimal
import java.util.*

class Event(
    val id: Long,
    val image: String,
    val title: String,
    val date: Date,
    val price: BigDecimal
)