package com.harrypotter.coredata.mapper

import com.harrypotter.coreapp.exceptions.GenericException
import com.harrypotter.coreapp.exceptions.NetworkException
import io.kotest.matchers.shouldBe
import org.junit.Test
import java.io.IOException
import java.net.UnknownHostException

class ExceptionsMapperTest {

    @Test
    fun `GIVEN an IOException WHEN toCustomException THEN should returns a NetworkException`() {
        val currentException = IOException()

        val realResult = currentException.toCustomException()

        val expectedResult = NetworkException("")
        realResult shouldBe expectedResult
    }

    @Test
    fun `GIVEN an UnknownHostException WHEN toCustomException THEN should returns a NetworkException`() {
        val currentException = UnknownHostException()

        val realResult = currentException.toCustomException()

        val expectedResult = NetworkException("")
        realResult shouldBe expectedResult
    }

    @Test
    fun `GIVEN an Exception WHEN toCustomException THEN should returns a GenericException`() {
        val currentException = Exception()

        val realResult = currentException.toCustomException()

        val expectedResult = GenericException("")
        realResult shouldBe expectedResult
    }
}