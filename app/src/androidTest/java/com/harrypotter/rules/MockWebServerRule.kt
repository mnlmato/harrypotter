package com.harrypotter.rules

import com.harrypotter.testdependencies.PORT_LOCALHOST
import okhttp3.mockwebserver.MockWebServer
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class MockWebServerRule : TestRule {

    val mockWebServer = MockWebServer()

    override fun apply(base: Statement, description: Description) = object : Statement() {
        override fun evaluate() {
            mockWebServer.start(PORT_LOCALHOST)
            try {
                base.evaluate()
            } finally {
                mockWebServer.shutdown()
            }
        }
    }
}