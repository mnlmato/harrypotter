package com.harrypotter.features.characters.main.ui

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.android.ide.common.rendering.api.SessionParams
import com.harrypotter.designsystem.theme.CustomTheme
import com.harrypotter.features.characters.main.ui.design.CharactersScreen
import com.harrypotter.features.characters.main.vm.model.CharactersState
import org.junit.Rule
import org.junit.Test

class CharactersErrorScreenTest {

    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.PIXEL_5,
        theme = "CustomTheme",
        renderingMode = SessionParams.RenderingMode.SHRINK,
        showSystemUi = false,
    )

    @Test
    fun `test characters list when state is error`() {
        paparazzi.snapshot {
            CustomTheme {
                CharactersScreen(
                    charactersState = CharactersState.UI.Error,
                    {},
                )
            }
        }
    }
}