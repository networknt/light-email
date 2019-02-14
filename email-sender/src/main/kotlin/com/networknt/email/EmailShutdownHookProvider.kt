package com.networknt.email

import com.networknt.kafka.streams.LightStreams
import com.networknt.server.ShutdownHookProvider
import com.networknt.service.SingletonServiceFactory

class EmailShutdownHookProvider : ShutdownHookProvider {
    override fun onShutdown() {
        val streams = SingletonServiceFactory.getBean(LightStreams::class.java) as EmailSenderStreams
        streams.close()
    }
}
