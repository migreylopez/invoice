package com.invoice.api.v0.model

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

data class Invoice(
        @JsonProperty("id")
        val id: UUID,
        @JsonProperty("created")
        @JsonFormat(
                timezone = "UTC",
                shape = JsonFormat.Shape.STRING,
                pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS]'Z'"
        )
        val created: LocalDateTime,
        @JsonProperty("currency")
        val currency: Currency,

        @JsonProperty("customerId")
        val customerId: UUID,
        @JsonProperty("customerName")
        val customerName: String,
        @JsonProperty("customerAddress")
        val customerAddress: String,

        @JsonProperty("productSku")
        val productSku: String,
        @JsonProperty("productName")
        val productName: String,
        @JsonProperty("productQuantity")
        val productQuantity: String,
        @JsonProperty("productPrice")
        val productPrice: BigDecimal
)