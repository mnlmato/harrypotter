package com.harrypotter.features.characters.main.data.repository.mapper

import com.harrypotter.coredata.mapper.ValueResponseMapper
import com.harrypotter.features.characters.main.domain.model.GenderType

object GenderValueResponseMapper : ValueResponseMapper<GenderType> {
    override fun toType(value: String) = when (value.lowercase()) {
        GENDER_MALE -> GenderType.MALE
        GENDER_FEMALE -> GenderType.FEMALE
        else -> GenderType.UNKNOWN
    }
}

private const val GENDER_MALE = "male"
private const val GENDER_FEMALE = "female"