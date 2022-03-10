package com.harrypotter.testdependencies

import com.harrypotter.coredata.UrlProvider

class UrlProviderTestImpl : UrlProvider {
    override val baseUrl = "http://localhost:$PORT_LOCALHOST/"
}

const val PORT_LOCALHOST = 8080