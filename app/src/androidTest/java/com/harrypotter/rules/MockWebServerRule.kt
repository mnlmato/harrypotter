package com.harrypotter.rules

import com.harrypotter.testdependencies.PORT_LOCALHOST
import okhttp3.mockwebserver.MockWebServer
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class MockWebServerRule : TestRule {

    lateinit var mockWebServer: MockWebServer
        private set

    override fun apply(base: Statement, description: Description?): Statement {
        return object : Statement() {
            override fun evaluate() {
                startServer()
                try {
                    base.evaluate()
                } finally {
                    stopServer()
                }
            }
        }
    }

    private fun startServer() {
        mockWebServer = MockWebServer().apply {
            start(PORT_LOCALHOST)
        }
    }

    private fun stopServer() {
        mockWebServer.shutdown()
    }
}