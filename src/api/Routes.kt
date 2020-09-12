package com.invoice.api

import com.invoice.api.v0.registerV0Routes
import io.ktor.locations.KtorExperimentalLocationsAPI
import io.ktor.routing.Route
import io.ktor.routing.route

@KtorExperimentalLocationsAPI
fun Route.registerAPIRoutes() {
    route("/v0") {
        registerV0Routes()
    }
}
