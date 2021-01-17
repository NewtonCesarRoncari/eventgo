package com.newton.eventgo.extensions

import java.util.*

fun Long.fromTimesTamp(): Date {
    return Date(this)
}