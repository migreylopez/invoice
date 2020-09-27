package com.invoice.api.v0.model

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime
import java.util.*

data class Invoice(
        @JsonProperty("id")
        @ApiModelProperty(required = true)
        val id: UUID,

        @JsonProperty("created", required = false)
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

        @JsonProperty("currency")
        val currency: Currency,

        @JsonProperty("customer")
        val customer: Customer,

        @JsonProperty("products")
        val products: Collection<Product>
)