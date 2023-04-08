package com.harrypotter.features.characters.main.ui.design

import com.harrypotter.features.characters.main.vm.model.CharacterUI

fun interface OnCharacterItemListener {
    fun onClick(character: CharacterUI)
}