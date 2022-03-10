package com.harrypotter.testdependencies.mockwebserver

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer

fun MockWebServer.getCharactersSuccessResponse() = MockResponse()
    .setResponseCode(HttpCodeType.SUCCESS_200.code)
    .setBody(readStringFromFile("characters_success_response.json"))

fun MockWebServer.getError(
    httpCodeType: HttpCodeType = HttpCodeType.ERROR_404
) = MockResponse().setResponseCode(httpCodeType.code)