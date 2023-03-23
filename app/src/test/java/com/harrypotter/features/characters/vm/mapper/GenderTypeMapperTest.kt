package com.harrypotter.features.characters.vm.mapper

import com.harrypotter.R
import com.harrypotter.coreui.resourceprovider.ResourceProvider
import com.harrypotter.features.characters.domain.model.GenderType
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert
import org.junit.Test

class GenderTypeMapperTest {

    private val resourceProvider: ResourceProvider = mockk()

    @Test
    fun `GIVEN a genderType WHEN toStringRes THEN should returns right string res`() {
        forAll(
            row(GenderType.MALE, R.string.gender_male),
            row(GenderType.FEMALE, R.string.gender_female),
            row(GenderType.UNKNOWN, R.string.gender_unknown),
        ) { genderType, stringRes ->
            every { resourceProvider.getString(stringRes) } returns "fooResultFromProvider"
            val realResult = genderType.mapToString(resourceProvider)

            val expectedResult = "fooResultFromProvider"
            Assert.assertEquals(expectedResult, realResult)
        }
    }
}