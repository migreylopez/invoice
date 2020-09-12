package com.invoice.api.v0

import com.invoice.api.v0.data.CreateInvoiceDTO
import io.ktor.application.call
import io.ktor.locations.KtorExperimentalLocationsAPI
import io.ktor.locations.Location
import io.ktor.locations.post
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import java.util.*

@KtorExperimentalLocationsAPI
fun Route.registerV0Routes() {
    post<Create>{
        val dto = call.receive<CreateInvoiceDTO>()
        call.respond(InvoiceService.create(dto))
    }
}

@Location("/create")
class Create()

@Location("/edit/{id}")
data class Edit(val id: UUID)