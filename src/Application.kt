package com.invoice

import com.fasterxml.jackson.annotation.JsonInclude
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.locations.*
import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.invoice.api.registerAPIRoutes
import io.ktor.jackson.*
import io.ktor.features.*
import io.ktor.util.DataConversionException
import java.util.*

fun main(args: Array<String>): Unit = io.ktor.server.jetty.EngineMain.main(args)

@KtorExperimentalLocationsAPI
@Suppress("unused") // Referenced in application.conf
fun Application.module() {
    install(Locations)
    install(CORS)
    {
        method(HttpMethod.Get)
        anyHost()
        allowNonSimpleContentTypes = true
    }

    install(ContentNegotiation) {
        jackson {
            setSerializationInclusion(JsonInclude.Include.NON_NULL)
            enable(SerializationFeature.INDENT_OUTPUT)
            registerModule(JavaTimeModule())
            disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE)
        }
    }

    // Support UUIDs in request paths
    install(DataConversion) {
        convert<UUID> {

            decode { values, _ ->
                values.singleOrNull()?.let { UUID.fromString(it) }
            }

            encode { value ->
                when (value) {
                    null -> listOf()
                    is UUID -> listOf(UUID.fromString(value.toString()).toString())
                    else -> throw DataConversionException("Cannot convert $value as UUID")
                }
            }
        }
    }

    routing {
        get("/health") {
            call.respondText("OK", contentType = ContentType.Text.Plain)
        }

        route("/api") {
            registerAPIRoutes()
        }
    }
}


