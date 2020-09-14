package com.invoice.api.v0

import com.invoice.api.v0.controller.CreateInvoiceController
import com.invoice.api.v0.controller.GetInvoiceController
import io.ktor.locations.KtorExperimentalLocationsAPI
import io.ktor.routing.Route

@KtorExperimentalLocationsAPI
fun Route.registerV0Routes() {
    // Register the controllers
    CreateInvoiceController.Controller(this)
    GetInvoiceController.Controller(this)
}