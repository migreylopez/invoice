package com.invoice.api.v0.data

import com.fasterxml.jackson.annotation.JsonProperty
import com.invoice.api.v0.model.Customer
import com.invoice.api.v0.model.Invoice
import com.invoice.api.v0.model.Product
import org.intellij.lang.annotations.Pattern
import org.jetbrains.annotations.NotNull
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

data class CreateInvoiceDTO(
        @JsonProperty("currency")
        val currency: Currency,

        @JsonProperty("customer")
        val customer: Customer,

        @JsonProperty("products")
        val products: Collection<Product>) {

    fun toInvoice(): Invoice {
        return Invoice(
                id = UUID.randomUUID(),
                created = LocalDateTime.now(),
                updated = null,
                deleted = null,
                currency = currency,
                customer = customer,
                products = products
        )
    }
}