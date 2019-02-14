package com.networknt.email.handler

import assertk.all
import assertk.assertThat
import mu.KotlinLogging
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(LightTestServer::class)
class CounterGetHandlerTest {

    companion object {
        val log = KotlinLogging.logger {}
    }

    @Test
    fun `test CounterGetHandler success`() {
        val response = LightTestServer.makeGetRequest("/counter")
        assertThat(response).all {
            rcIsEqualTo(200)
            bodyContains("count")

        }
    }

}