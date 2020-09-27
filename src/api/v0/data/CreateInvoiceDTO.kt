package com.invoice.api.v0.data

import com.fasterxml.jackson.annotation.JsonProperty
import com.invoice.api.v0.model.Customer
import com.invoice.api.v0.model.Invoice
import com.invoice.api.v0.model.Product
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime
import java.util.*

data class CreateInvoiceDTO(
        @JsonProperty("currency")
        @ApiModelProperty(required = true)
        val currency: Currency,

        @JsonProperty("customer")
        @ApiModelProperty(required = true)
        val customer: Customer,

        @JsonProperty("products")
        @ApiModelProperty(required = true)
        val products: Collection<Product>) {

    fun toInvoice(): Invoice {
        return Invoice(
                id = UUID.randomUUID(),
                created = LocalDateTime.now(),
                updated = null,
                currency = currency,
                customer = customer,
                products = products
        )
    }
}