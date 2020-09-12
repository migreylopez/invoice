package com.invoice.api.v0

import com.invoice.api.v0.data.CreateInvoiceDTO
import com.invoice.api.v0.data.toInvoice
import java.util.*

object InvoiceService {
    fun create(dto : CreateInvoiceDTO) : Map<String, UUID> {
        // TODO: Validate invoice data? Maybe a new controller that registers its route himself
        val invoice = dto.toInvoice()

        // TODO: Divide the products stuff into a Products array. Any benefit on doing the same for the Customer?

        // TODO: store in the database
        return mapOf("id" to invoice.id)
    }
}