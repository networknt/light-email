package com.networknt.email.handler

import com.networknt.handler.LightHttpHandler
import io.undertow.server.HttpServerExchange
import io.undertow.util.HttpString

class CounterGetHandler : LightHttpHandler {
    @Throws(Exception::class)
    override fun handleRequest(exchange: HttpServerExchange) {
        exchange.responseHeaders.add(HttpString("Content-Type"), "application/json")
        exchange.responseSender.send("{\"count\":100}")
    }
}
