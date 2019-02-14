package com.networknt.email

import com.networknt.config.Config
import com.networknt.kafka.streams.KafkaStreamsConfig
import com.networknt.kafka.streams.LightStreams
import mu.KotlinLogging
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.KafkaStreams
import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.StreamsConfig
import org.apache.kafka.streams.processor.Processor
import org.apache.kafka.streams.processor.ProcessorSupplier
import org.apache.kafka.streams.state.Stores
import java.util.*

class EmailSenderStreams : LightStreams {

    internal val config = Config.getInstance().getJsonObjectConfig(KafkaStreamsConfig.CONFIG_NAME, KafkaStreamsConfig::class.java) as KafkaStreamsConfig

    // this variable will be initialized during server startup from the startup hook provider.
    lateinit var emailSenderStreams: KafkaStreams

    companion object {
        val log = KotlinLogging.logger {}
    }

    override fun start(ip: String, port: Int) {
        if (log.isDebugEnabled()) log.debug("EmailSenderStreams is starting...")
        startEmailStreams()
    }

    override fun close() {
        if (log.isDebugEnabled()) log.debug("EmailSenderStreams is closing...")
        emailSenderStreams.close()
    }

    private fun startEmailStreams() {

        val builder = StreamsBuilder()
        /*
        val keyValueKycIdStoreBuilder = Stores.keyValueStoreBuilder(Stores.persistentKeyValueStore(kycId),
                Serdes.String(),
                Serdes.String())
        builder.addStateStore(keyValueKycIdStoreBuilder)

        val keyValueKycAddressStoreBuilder = Stores.keyValueStoreBuilder(Stores.persistentKeyValueStore(kycAddress),
                Serdes.String(),
                Serdes.String())
        builder.addStateStore(keyValueKycAddressStoreBuilder)

        val keyValueKycEmailStoreBuilder = Stores.keyValueStoreBuilder(Stores.persistentKeyValueStore(kycEmail),
                Serdes.String(),
                Serdes.String())
        builder.addStateStore(keyValueKycEmailStoreBuilder)

        val keyValueKycHistoryStoreBuilder = Stores.keyValueStoreBuilder(Stores.persistentKeyValueStore(kycHistory),
                Serdes.String(),
                Serdes.String())
        builder.addStateStore(keyValueKycHistoryStoreBuilder)

        builder.stream<Any, Any>("event").process(object : ProcessorSupplier {
            override fun get(): Processor<*, *> {
                return KycReaderStreams.KycEventProcessor()
            }
        }, kycId, kycAddress, kycEmail, kycHistory)
        */
        val topology = builder.build()

        var streamsProps: Properties = Properties()
        streamsProps[StreamsConfig.BOOTSTRAP_SERVERS_CONFIG] = config.bootstrapServers
        streamsProps[StreamsConfig.APPLICATION_ID_CONFIG] = config.applicationId
        streamsProps[StreamsConfig.PROCESSING_GUARANTEE_CONFIG] = config.processingGuarantee
        streamsProps[StreamsConfig.REPLICATION_FACTOR_CONFIG] = config.replicationFactor
        streamsProps[ConsumerConfig.AUTO_OFFSET_RESET_CONFIG] = "earliest";

        emailSenderStreams = KafkaStreams(topology, streamsProps)
        emailSenderStreams.cleanUp()
        emailSenderStreams.start()
    }

}