package com.harrypotter.testdependencies.mockwebserver

enum class HttpCodeType(val code: Int) {
    SUCCESS_200(200),
    ERROR_404(404),
}