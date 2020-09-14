package com.invoice.api.v0.controller

import com.invoice.api.v0.InvoiceService
import com.invoice.api.v0.data.CreateInvoiceDTO
import io.ktor.application.call
import io.ktor.locations.KtorExperimentalLocationsAPI
import io.ktor.locations.Location
import io.ktor.locations.post
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route

class CreateInvoiceController {
    @KtorExperimentalLocationsAPI
    class Controller (route: Route) {
        init {
            route.post<Routes.Create> {
                val dto = call.receive<CreateInvoiceDTO>()
                // TODO: Validate invoice data?
                call.respond(InvoiceService.create(dto.toInvoice()))
            }
        }
    }

    object Routes {
        @KtorExperimentalLocationsAPI
        @Location("/create")
        class Create()
    }
}