package com.newton.eventgo.models.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import java.math.BigDecimal

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(
    "people",
    "date",
    "description",
    "image",
    "longitude",
    "latitude",
    "price",
    "title",
    "id"
)
class EventDetailRequest {

    var id: String? = ""
    var people: List<Any>? = null
    var date: Long? = null
    var description: String? = null
    var image: String? = null
    var longitude: Double? = null
    var latitude: Double? = null
    var price: BigDecimal? = null
    var title: String? = null
}