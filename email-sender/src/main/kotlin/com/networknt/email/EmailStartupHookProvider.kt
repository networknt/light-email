package com.networknt.email

import com.networknt.kafka.streams.LightStreams
import com.networknt.server.Server
import com.networknt.server.StartupHookProvider
import com.networknt.service.SingletonServiceFactory
import com.networknt.utility.NetUtils

class KycStartupHookProvider : StartupHookProvider {

    override fun onStartup() {
        // get host and port for this reader instance.
        val port = Server.config.httpsPort
        val ip = NetUtils.getLocalAddressByDatagram()
        println("ip = $ip port = $port")

        val streams = SingletonServiceFactory.getBean(LightStreams::class.java) as EmailSenderStreams
        // start the kafka stream process
        streams.start(ip, port)
    }
}
