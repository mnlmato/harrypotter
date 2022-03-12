package com.harrypotter.features.characters.vm.mapper

import com.harrypotter.R
import com.harrypotter.features.characters.domain.model.GenderType

fun GenderType.toStringRes() = when (this) {
    GenderType.MALE -> R.string.gender_male
    GenderType.FEMALE -> R.string.gender_female
    else -> R.string.gender_unknown
}