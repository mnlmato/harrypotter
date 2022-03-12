package com.harrypotter.coredata.mapper

interface ValueResponseMapper<T : Any> {
    val value: String

    fun toType(): T
}