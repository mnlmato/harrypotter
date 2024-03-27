package com.harrypotter.testdependencies.mockwebserver

import androidx.test.platform.app.InstrumentationRegistry
import com.harrypotter.coredata.JsonReader
import com.harrypotter.features.characters.commons.data.datasource.local.CHARACTERS_SUCCESS_RESPONSE_JSON
import dagger.hilt.android.testing.HiltTestApplication
import okhttp3.mockwebserver.MockResponse

object CharactersMockResponseProvider {

    private val jsonReader: JsonReader =
        JsonReader(context = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as HiltTestApplication)

    fun provideSuccessCharactersList(): MockResponse {
        return MockResponse()
            .setResponseCode(HttpCodeType.SUCCESS_200.code)
            .setBody(jsonReader.read(CHARACTERS_SUCCESS_RESPONSE_JSON))
    }

    fun provideError(httpCodeType: HttpCodeType = HttpCodeType.ERROR_404): MockResponse {
        return MockResponse().setResponseCode(httpCodeType.code)
    }
}