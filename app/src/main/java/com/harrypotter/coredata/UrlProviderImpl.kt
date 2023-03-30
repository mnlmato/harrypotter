package com.harrypotter.coredata

import javax.inject.Inject

class UrlProviderImpl @Inject constructor() : UrlProvider {
    override val baseUrl = BASE_URL
}

private const val BASE_URL = "https://hp-api.onrender.com/api/"
