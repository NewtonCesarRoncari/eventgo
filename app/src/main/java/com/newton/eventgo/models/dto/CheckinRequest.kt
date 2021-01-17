package com.newton.eventgo.models.dto

import com.fasterxml.jackson.annotation.*
import java.util.*


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("eventId", "name", "email")
class CheckinRequest(
    var eventId: Long? = null,
    var name: String? = null,
    var email: String? = null
) {

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