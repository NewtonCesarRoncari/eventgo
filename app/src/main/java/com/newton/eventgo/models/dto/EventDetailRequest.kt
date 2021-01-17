package com.newton.eventgo.models.dto

import com.fasterxml.jackson.annotation.*
import com.newton.eventgo.models.Event
import java.math.BigDecimal
import java.util.*

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

    @JsonIgnore
    private val additionalProperties: MutableMap<String, Any> = HashMap()

    @JsonAnyGetter
    fun getAdditionalProperties(): Map<String, Any> {
        return additionalProperties
    }

    @JsonAnySetter
    fun setAdditionalProperty(name: String, value: Any) {
        additionalProperties[name] = value
    }
}