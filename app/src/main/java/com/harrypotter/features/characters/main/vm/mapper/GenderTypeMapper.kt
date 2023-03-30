package com.harrypotter.features.characters.main.vm.mapper

import com.harrypotter.R
import com.harrypotter.coreui.resourceprovider.ResourceProvider
import com.harrypotter.features.characters.main.domain.model.GenderType

fun GenderType.mapToString(resourceProvider: ResourceProvider): String {
    val genderResId = when (this) {
        GenderType.MALE -> R.string.gender_male
        GenderType.FEMALE -> R.string.gender_female
        else -> R.string.gender_unknown
    }
    return resourceProvider.getString(genderResId)
}