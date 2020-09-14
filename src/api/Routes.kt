package com.invoice.api

import com.invoice.api.v0.controller.InvoicesController
import io.ktor.locations.KtorExperimentalLocationsAPI
import io.ktor.routing.Route
import io.ktor.routing.route

@KtorExperimentalLocationsAPI
fun Route.registerAPIRoutes() {
    route("/v0") {
        InvoicesController.Controller(this)
    }
}
