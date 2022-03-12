package com.harrypotter.features.characters.data.repository.mapper

import com.harrypotter.coredata.mapper.ValueResponseMapper
import com.harrypotter.features.characters.domain.model.HouseType

object HouseValueResponseMapper : ValueResponseMapper<HouseType> {
    override fun toType(value: String) = when (value.lowercase()) {
        HOUSE_GRYFFINDOR -> HouseType.GRYFFINDOR
        HOUSE_SLYTHERIN -> HouseType.SLYTHERIN
        HOUSE_HUFFLEPUFF -> HouseType.HUFFLEPUFF
        HOUSE_RAVENCLAW -> HouseType.RAVENCLAW
        else -> HouseType.UNKNOWN
    }
}

private const val HOUSE_GRYFFINDOR = "gryffindor"
private const val HOUSE_SLYTHERIN = "slytherin"
private const val HOUSE_HUFFLEPUFF = "hufflepuff"
private const val HOUSE_RAVENCLAW = "ravenclaw"
