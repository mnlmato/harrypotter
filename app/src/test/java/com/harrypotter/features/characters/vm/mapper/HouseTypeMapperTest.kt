package com.harrypotter.features.characters.vm.mapper

import com.harrypotter.R
import com.harrypotter.features.characters.domain.model.HouseType
import io.kotest.data.row
import io.kotest.data.blocking.forAll
import org.junit.Assert
import org.junit.Test

class HouseTypeMapperTest {

    @Test
    fun `GIVEN a houseType WHEN toStringRes THEN should returns right string res`() {
        forAll(
            row(HouseType.GRYFFINDOR, R.string.house_gryffindor),
            row(HouseType.GRYFFINDOR, R.string.house_gryffindor),
            row(HouseType.SLYTHERIN, R.string.house_slytherin),
            row(HouseType.HUFFLEPUFF, R.string.house_hufflepuff),
            row(HouseType.RAVENCLAW, R.string.house_ravenclaw),
            row(HouseType.UNKNOWN, R.string.house_unknown),
        ) { houseType, stringRes ->
            val realResult = houseType.toStringRes()

            val expectedResult: Int = stringRes
            Assert.assertEquals(expectedResult, realResult)
        }
    }
}