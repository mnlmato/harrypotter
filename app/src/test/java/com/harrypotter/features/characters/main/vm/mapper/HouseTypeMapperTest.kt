package com.harrypotter.features.characters.main.vm.mapper

import com.harrypotter.R
import com.harrypotter.coreui.resourceprovider.ResourceProvider
import com.harrypotter.features.characters.main.domain.model.HouseType
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert
import org.junit.Test

class HouseTypeMapperTest {

    private val resourceProvider: ResourceProvider = mockk()

    @Test
    fun `GIVEN a houseType WHEN toStringRes THEN should returns right string res`() {
        forAll(
            row(HouseType.GRYFFINDOR, R.string.house_gryffindor),
            row(HouseType.SLYTHERIN, R.string.house_slytherin),
            row(HouseType.HUFFLEPUFF, R.string.house_hufflepuff),
            row(HouseType.RAVENCLAW, R.string.house_ravenclaw),
            row(HouseType.UNKNOWN, R.string.house_unknown),
        ) { houseType, stringRes ->
            every { resourceProvider.getString(stringRes) } returns "fooResultFromProvider"
            val realResult = houseType.mapToString(resourceProvider)

            val expectedResult = "fooResultFromProvider"
            Assert.assertEquals(expectedResult, realResult)
        }
    }
}