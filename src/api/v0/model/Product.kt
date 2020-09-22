package com.invoice.api.v0.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

data class Product(
        @JsonProperty("sku")
        val sku: String,
        @JsonProperty("name")
        val name: String,
        @JsonProperty("quantity")
        val quantity: Int,
        @JsonProperty("price")
        val price: BigDecimal
)