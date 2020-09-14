package com.invoice.api.v0.model

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonSerialize
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

        @JsonProperty("updated", required = false)
        @JsonFormat(
                timezone = "UTC",
                shape = JsonFormat.Shape.STRING,
                pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS]'Z'"
        )
        val updated: LocalDateTime?,

        @JsonProperty("deleted", required = false)
        @JsonFormat(
                timezone = "UTC",
                shape = JsonFormat.Shape.STRING,
                pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS]'Z'"
        )
        val deleted: LocalDateTime?,

        @JsonProperty("currency")
        val currency: Currency,

        @JsonProperty("customer")
        val customer: Customer,

        @JsonProperty("products")
        val products: Collection<Product>
)