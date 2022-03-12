package com.harrypotter.features.characters.data.repository.mapper

import com.harrypotter.coredata.mapper.ValueResponseMapper
import com.harrypotter.features.characters.domain.model.GenderType

@JvmInline
value class GenderResponseMapper(override val value: String) : ValueResponseMapper<GenderType> {
    override fun toType() = when (value.lowercase()) {
        GENDER_MALE -> GenderType.MALE
        GENDER_FEMALE -> GenderType.FEMALE
        else -> GenderType.UNKNOWN
    }
}

private const val GENDER_MALE = "male"
private const val GENDER_FEMALE = "female"