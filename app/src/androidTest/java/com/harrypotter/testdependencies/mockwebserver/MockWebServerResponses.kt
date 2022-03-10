package com.harrypotter.testdependencies.mockwebserver

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer

fun MockWebServer.getCharactersSuccessResponse() = MockResponse().setResponseCode(200)
    .setBody(readStringFromFile("characters_success_response.json"))

fun MockWebServer.getError(
    httpError: HttpError = HttpError.ERROR_404
) = MockResponse().setResponseCode(httpError.code)