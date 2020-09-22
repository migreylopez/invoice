package com.invoice.api.v0

import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.model.AttributeAction
import com.amazonaws.services.dynamodbv2.model.AttributeValue
import com.amazonaws.services.dynamodbv2.model.AttributeValueUpdate
import com.amazonaws.services.dynamodbv2.model.DeleteItemRequest
import com.amazonaws.services.dynamodbv2.model.ReturnValue
import com.amazonaws.services.dynamodbv2.model.ScanRequest
import com.amazonaws.services.dynamodbv2.model.UpdateItemRequest
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.invoice.api.v0.model.Invoice
import java.time.LocalDateTime
import java.util.*


object DynamoDBClient {
    private const val table_name = "Invoice"
    private val objectMapper = ObjectMapper().configure()

    private val client: AmazonDynamoDB by lazy {
        AmazonDynamoDBClientBuilder
                .standard()
                .withEndpointConfiguration(AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "local"))
                .build()
    }

    fun put(invoice: Invoice) {
        println("Storing Invoice: ${invoice.id} at ${invoice.created} to DynamoDB table $table_name.")
        val attributeValue = mapOf(
                "id" to AttributeValue(invoice.id.toString()),
                "invoice" to invoice.toAttributeValue())
        client.putItem(table_name, attributeValue)
    }

    fun get(id: UUID): Invoice? {
        println("Getting Invoice $id from DynamoDB table $table_name.")
        return client
                .getItem(table_name, mapOf("id" to AttributeValue(id.toString())))?.item?.toInvoice()
    }

    fun update(invoice: Invoice): Boolean {
        println("Updating Invoice ${invoice.id} from DynamoDB table $table_name.")
        get(invoice.id)?.let {
            // Invoice exists
            val updatedInvoice = invoice.copy(updated = LocalDateTime.now())
            val updateItemRequest = UpdateItemRequest(
                    table_name,
                    mapOf("id" to AttributeValue(updatedInvoice.id.toString())),
                    mapOf("invoice" to AttributeValueUpdate(updatedInvoice.toAttributeValue(), AttributeAction.PUT)),
                    ReturnValue.NONE
            )

            client.updateItem(updateItemRequest)
            return true
        }
        return false
    }

    fun getAll(): List<Invoice> {
        println("Retrieving all the invoices from $table_name")
        return client
                .scan(ScanRequest(table_name))
                .items
                .mapNotNull {
                    it.toInvoice()
                }
    }

    fun delete(id: UUID): Boolean {
        return client.deleteItem(
                DeleteItemRequest(table_name, mapOf("id" to AttributeValue(id.toString())), ReturnValue.ALL_OLD)
        ).attributes.isNotEmpty()
    }

    private fun Invoice.toAttributeValue() = AttributeValue(objectMapper.writeValueAsString(this))

    private fun Map<String, AttributeValue>.toInvoice(): Invoice? {
        return this["invoice"]?.let {
            objectMapper.readValue(it.s, Invoice::class.java)
        }
    }

    private fun ObjectMapper.configure(): ObjectMapper {
        return this.registerModule(JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE)
    }
}




