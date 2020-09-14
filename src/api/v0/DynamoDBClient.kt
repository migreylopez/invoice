package com.concur.t2.rvr.datastore

import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.model.AttributeValue
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.invoice.api.v0.model.Invoice
import java.util.*

object DynamoDBClient {
    private const val tableName = "Invoice"
    private val objectMapper = ObjectMapper().configure()

    private val client: AmazonDynamoDB by lazy {
        AmazonDynamoDBClientBuilder
                .standard()
                .withEndpointConfiguration(AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "local"))
                .build()
    }

    fun put(invoice: Invoice) {
        println("Storing Invoice: ${invoice.id} at ${invoice.created} to DynamoDB table $tableName.")
        val attributeValue = mapOf(
                "id" to AttributeValue(invoice.id.toString()),
                "invoice" to AttributeValue(objectMapper.writeValueAsString(invoice))
        )
        client.putItem(tableName, attributeValue)
    }

    fun get(id: UUID): Invoice? {
        println("Getting Invoice $id from DynamoDB table $tableName.")
        return client
                .getItem(tableName, mapOf("id" to AttributeValue(id.toString())))
                .item?.let { item ->
                    item["invoice"]?.let { value ->
                        objectMapper.readValue(value.s, Invoice::class.java)
                    }
                }
    }

    private fun ObjectMapper.configure(): ObjectMapper {
        return this.registerModule(JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE)
    }

}
