package com.invoice.api.v0.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class Customer(
        @JsonProperty("id")
        val id: UUID,
        @JsonProperty("name")
        val name: String,
        @JsonProperty("address")
        val address: String
)