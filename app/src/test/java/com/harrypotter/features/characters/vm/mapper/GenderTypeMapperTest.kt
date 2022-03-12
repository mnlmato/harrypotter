package com.harrypotter.features.characters.vm.mapper

import com.harrypotter.R
import com.harrypotter.features.characters.domain.model.GenderType
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import org.junit.Assert
import org.junit.Test

class GenderTypeMapperTest {

    @Test
    fun `GIVEN a genderType WHEN toStringRes THEN should returns right string res`() {
        forAll(
            row(GenderType.MALE, R.string.gender_male),
            row(GenderType.FEMALE, R.string.gender_female),
            row(GenderType.UNKNOWN, R.string.gender_unknown),
        ) { genderType, stringRes ->
            val realResult = genderType.toStringRes()

            val expectedResult: Int = stringRes
            Assert.assertEquals(expectedResult, realResult)
        }
    }
}