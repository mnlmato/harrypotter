package com.harrypotter.features.characters.commons.data.datasource.local

import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import com.harrypotter.coredata.JsonReader
import com.harrypotter.features.characters.commons.data.datasource.api.model.CharacterResponse
import javax.inject.Inject

class CharactersLocalDatasource @Inject constructor(
    private val gson: Gson,
    private val jsonReader: JsonReader,
) {
    fun getCharacters(): List<CharacterResponse> {
        val charactersString = jsonReader.read(CHARACTERS_SUCCESS_RESPONSE_JSON)
        return try {
            gson.fromJson(charactersString, object : TypeToken<List<CharacterResponse>>() {}.type)
        } catch (ex: JsonParseException) {
            emptyList()
        } catch (ex: JsonSyntaxException) {
            emptyList()
        }
    }
}

const val CHARACTERS_SUCCESS_RESPONSE_JSON = "characters_success_response.json"