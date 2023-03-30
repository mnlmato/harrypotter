package com.harrypotter.coredata.mapper

interface ValueResponseMapper<T : Any> {
    fun toType(value: String): T
}