package com.invoice.api.v0.controller

import com.invoice.api.v0.InvoiceService
import com.invoice.api.v0.data.CreateInvoiceDTO
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.locations.KtorExperimentalLocationsAPI
import io.ktor.locations.Location
import io.ktor.locations.get
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import java.util.*

class GetInvoiceController {
    @KtorExperimentalLocationsAPI
    class Controller (route: Route) {
        init {
            route.get<Routes.Get> {
                val id = UUID.fromString(call.parameters["id"]!!)
                InvoiceService.get(id)?.let {
                    call.respond(it)
                } ?: call.respond(HttpStatusCode.NotFound)
            }
        }
    }

    object Routes {
        @KtorExperimentalLocationsAPI
        @Location("/get/{id}")
        class Get(val id : UUID)
    }
}