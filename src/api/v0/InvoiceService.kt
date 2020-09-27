package com.invoice.api.v0

import com.invoice.api.v0.model.Invoice
import java.util.*

object InvoiceService {
    fun create(invoice : Invoice) {
        DynamoDBClient.put(invoice)
    }

    fun get(id : UUID) : Invoice? {
        return DynamoDBClient.get(id)
    }

    fun edit(invoice : Invoice) : Boolean {
        return DynamoDBClient.update(invoice)
    }

    fun getAll() : List<Invoice> {
        return DynamoDBClient.getAll()
    }

    fun delete(id: UUID) : Boolean {
        return DynamoDBClient.delete(id)
    }
}