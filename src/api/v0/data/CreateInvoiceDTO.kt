package com.invoice.api.v0.data

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import com.invoice.api.v0.model.Invoice
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

data class CreateInvoiceDTO (
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

fun CreateInvoiceDTO.toInvoice() : Invoice {
    return Invoice(
            id = UUID.randomUUID(),
            created = LocalDateTime.now(),
            currency = currency,
            customerId = customerId,
            customerName = customerName,
            customerAddress = customerAddress,
            productSku = productSku,
            productName = productName,
            productQuantity = productQuantity,
            productPrice = productPrice
    )
}