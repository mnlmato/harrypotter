package com.harrypotter.testdependencies

import com.harrypotter.coredata.UrlProvider

class UrlProviderTestImpl : UrlProvider {
    override val baseUrl = "http://localhost:8080/"
}