package com.networknt.email.handler

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.networknt.client.Http2Client
import mu.KotlinLogging
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(LightTestServer::class)
class HealthHandlerTest {

    companion object {
        val log = KotlinLogging.logger {}
    }

    @Test
    fun `test call health check`() {
        val response = LightTestServer.makeGetRequest("/health/com.networknt.email-1.0.0")
        assertThat(response.responseCode, "RC").isEqualTo(200)
        assertThat(response.getAttachment(Http2Client.RESPONSE_BODY), "Body").isEqualTo("OK")
    }
}
