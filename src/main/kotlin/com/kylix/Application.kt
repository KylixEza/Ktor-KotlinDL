package com.kylix

import com.kylix.deep_learning.KotlinDLHelper
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.kylix.plugins.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "localhost", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    KotlinDLHelper.init()
    configureSerialization()
    configureRouting()
}
