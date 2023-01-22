package com.kylix.plugins

import com.kylix.deep_learning.KotlinDLHelper
import com.kylix.deep_learning.buildProperImageSize
import com.kylix.deep_learning.toFloatArray
import com.kylix.model.PredictResponse
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*

fun Application.configureRouting() {

    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        post("/predict") {
            val multipart = call.receiveMultipart()
            var result = ""

            try {
                multipart.forEachPart { part ->
                    if (part is PartData.FileItem) {
                        val byteArray = part.streamProvider().readBytes()
                        val image = buildProperImageSize(byteArray).toFloatArray()
                        result = KotlinDLHelper.predict(image)
                    }
                }
                call.respond(
                    HttpStatusCode.OK,
                    PredictResponse(
                        HttpStatusCode.OK.value,
                        result,
                        HttpStatusCode.OK.description
                    )
                )
            } catch (e: Exception) {
                e.printStackTrace()
                call.respond(
                    HttpStatusCode.InternalServerError,
                    PredictResponse(
                        HttpStatusCode.InternalServerError.value,
                        e.message ?: "Unknown error",
                        HttpStatusCode.InternalServerError.description
                    )
                )
            }
        }
    }
}
