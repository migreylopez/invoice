package com.invoice.api.v0.controller

import com.invoice.api.v0.InvoiceService
import com.invoice.api.v0.data.CreateInvoiceDTO
import com.invoice.api.v0.model.Invoice
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.locations.KtorExperimentalLocationsAPI
import io.ktor.locations.Location
import io.ktor.locations.get
import io.ktor.locations.post
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import java.util.*

@KtorExperimentalLocationsAPI
class InvoicesController {
    class Controller (route: Route) {
        init {
            route.post<Routes.Create> {
                val createInvoiceDTO = call.receive<CreateInvoiceDTO>()
                // TODO: Validate invoice data?
                call.respond(InvoiceService.create(createInvoiceDTO.toInvoice()))
            }

            route.get<Routes.Get> {
                val invoiceId = UUID.fromString(call.parameters["invoiceId"]!!)
                InvoiceService.get(invoiceId)?.let {
                    call.respond(it)
                } ?: call.respond(HttpStatusCode.NotFound)
            }

            route.post<Routes.Edit> {
                val invoice = call.receive<Invoice>()
                // TODO: Validate invoice data?
                if (InvoiceService.edit(invoice)) {
                    call.respond(HttpStatusCode.NoContent)
                } else {
                    call.respond(HttpStatusCode.BadRequest)
                }
            }

            route.get<Routes.All> {
                call.respond(InvoiceService.getAll())
            }

            route.get<Routes.Delete> {
                val invoiceId = UUID.fromString(call.parameters["invoiceId"]!!)
                if (InvoiceService.delete(invoiceId)) {
                    call.respond(HttpStatusCode.NoContent)
                } else {
                    call.respond(HttpStatusCode.NotFound)
                }
            }
        }
    }

    object Routes {
        @Location("/create")
        class Create

        @Location("/get/{invoiceId}")
        class Get(val invoiceId : UUID)

        @Location("/edit")
        class Edit

        @Location("/all")
        class All

        @Location("/delete/{invoiceId}")
        class Delete(val invoiceId: UUID)
    }
}