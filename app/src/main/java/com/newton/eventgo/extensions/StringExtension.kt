package com.newton.eventgo.extensions

import com.newton.eventgo.exception.NegativeLimitInStringException

fun String.limit(maxCharacters: Int): String {
    if (maxCharacters < 0) throw NegativeLimitInStringException()
    if (this.length > maxCharacters) {
        val firstCharacter = 0
        return "${this.substring(firstCharacter, maxCharacters)}..."
    }
    return this
}