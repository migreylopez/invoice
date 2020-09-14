package com.invoice.api.v0

import com.concur.t2.rvr.datastore.DynamoDBClient
import com.invoice.api.v0.model.Invoice
import java.util.*

object InvoiceService {
    fun create(invoice : Invoice) : Map<String, UUID> {
        DynamoDBClient.put(invoice)
        return mapOf("id" to invoice.id)
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
}