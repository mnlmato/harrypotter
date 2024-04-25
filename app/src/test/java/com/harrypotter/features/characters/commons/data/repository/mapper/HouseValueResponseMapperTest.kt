package com.harrypotter.features.characters.commons.data.repository.mapper

import com.harrypotter.features.characters.main.domain.model.HouseType
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import org.junit.Test

class HouseValueResponseMapperTest {

    @Test
    fun `GIVEN a house value WHEN toType THEN should returns right house type`() {
        forAll(
            row(HOUSE_GRYFFINDOR, HouseType.GRYFFINDOR),
            row(HOUSE_SLYTHERIN, HouseType.SLYTHERIN),
            row(HOUSE_HUFFLEPUFF, HouseType.HUFFLEPUFF),
            row(HOUSE_RAVENCLAW, HouseType.RAVENCLAW),
            row("", HouseType.UNKNOWN),
        ) { value, houseType ->
            val realResult = HouseValueResponseMapper.toType(value)

            val expectedResult: HouseType = houseType
            realResult shouldBe expectedResult
        }
    }
}

private const val HOUSE_GRYFFINDOR = "gryffindor"
private const val HOUSE_SLYTHERIN = "slytherin"
private const val HOUSE_HUFFLEPUFF = "hufflepuff"
private const val HOUSE_RAVENCLAW = "ravenclaw"