package com.networknt.email.handler

import assertk.all
import assertk.assertThat
import mu.KotlinLogging
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(LightTestServer::class)
class EmailGetHandlerTest {

    companion object {
        val log = KotlinLogging.logger {}
    }

    @Test
    fun `test EmailGetHandler success`() {
        val response = LightTestServer.makeGetRequest("/email")
        assertThat(response).all {
            rcIsEqualTo(200)
            bodyContains("from")
            bodyContains("to")
            bodyContains("body")
        }
    }

}