package com.harrypotter.features.characters.main.vm.mapper

import com.harrypotter.R
import com.harrypotter.coreui.resourceprovider.ResourceProvider
import com.harrypotter.features.characters.main.domain.model.HouseType

fun HouseType.mapToString(resourceProvider: ResourceProvider): String {
    val houseResId = when (this) {
        HouseType.GRYFFINDOR -> R.string.house_gryffindor
        HouseType.SLYTHERIN -> R.string.house_slytherin
        HouseType.HUFFLEPUFF -> R.string.house_hufflepuff
        HouseType.RAVENCLAW -> R.string.house_ravenclaw
        else -> R.string.house_unknown
    }
    return resourceProvider.getString(houseResId)
}