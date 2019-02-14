package com.networknt.email.handler

import com.networknt.handler.LightHttpHandler
import io.undertow.util.HttpString
import io.undertow.server.HttpServerExchange

class SenderGetHandler : LightHttpHandler {

    @Throws(Exception::class)
    override fun handleRequest(exchange: HttpServerExchange) {
        exchange.responseHeaders.add(HttpString("Content-Type"), "application/json")
        exchange.responseSender.send("{\"noreply@networknt.com\":1000,\"noreply@lightapi.net\":93,\"noreply@taiji.io\":19832}")
    }
}
