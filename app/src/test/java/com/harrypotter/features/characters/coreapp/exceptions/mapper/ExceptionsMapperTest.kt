package com.harrypotter.features.characters.coreapp.exceptions.mapper

import com.harrypotter.coreapp.exceptions.GenericException
import com.harrypotter.coreapp.exceptions.NetworkException
import com.harrypotter.coreapp.exceptions.mapper.toCustomException
import org.junit.Assert
import org.junit.Test
import java.io.IOException
import java.net.UnknownHostException

class ExceptionsMapperTest {

    @Test
    fun `GIVEN an IOException WHEN toCustomException THEN should returns a NetworkException`() {
        val currentException = IOException()

        val realResult = currentException.toCustomException()

        val expectedResult = NetworkException("")
        Assert.assertEquals(expectedResult, realResult)
    }

    @Test
    fun `GIVEN an UnknownHostException WHEN toCustomException THEN should returns a NetworkException`() {
        val currentException = UnknownHostException()

        val realResult = currentException.toCustomException()

        val expectedResult = NetworkException("")
        Assert.assertEquals(expectedResult, realResult)
    }

    @Test
    fun `GIVEN an Exception WHEN toCustomException THEN should returns a GenericException`() {
        val currentException = Exception()

        val realResult = currentException.toCustomException()

        val expectedResult = GenericException("")
        Assert.assertEquals(expectedResult, realResult)
    }
}