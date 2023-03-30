package com.harrypotter.features.characters.data.repository.mapper

import com.harrypotter.features.characters.domain.model.GenderType
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import org.junit.Assert
import org.junit.Test

class GenderValueResponseMapperTest {

    @Test
    fun `GIVEN a gender value WHEN toType THEN should returns right gender type`() {
        forAll(
            row(GENDER_MALE, GenderType.MALE),
            row(GENDER_FEMALE, GenderType.FEMALE),
            row("", GenderType.UNKNOWN),
        ) { value, genderType ->
            val realResult = GenderValueResponseMapper.toType(value)

            val expectedResult: GenderType = genderType
            Assert.assertEquals(expectedResult, realResult)
        }
    }
}

private const val GENDER_MALE = "male"
private const val GENDER_FEMALE = "female"