package com.harrypotter.features.characters.commons.data.repository.mapper

import com.harrypotter.features.characters.main.domain.model.GenderType
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

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
            Assertions.assertEquals(expectedResult, realResult)
        }
    }
}

private const val GENDER_MALE = "male"
private const val GENDER_FEMALE = "female"