package com.networknt.email.handler

import com.networknt.handler.LightHttpHandler
import io.undertow.util.HttpString
import io.undertow.server.HttpServerExchange

class EmailGetHandler : LightHttpHandler {

    @Throws(Exception::class)
    override fun handleRequest(exchange: HttpServerExchange) {
        exchange.responseHeaders.add(HttpString("Content-Type"), "application/json")
        exchange.responseSender.send("{\"from\":\"noreply@networknt.com\",\"to\":\"stevehu@gmail.com\",\"body\":\"Hello World\"}")
    }
}
