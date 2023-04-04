package com.harrypotter.features.characters.detail.ui.design

import android.annotation.SuppressLint
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.harrypotter.R
import com.harrypotter.coreui.image.NetworkImage
import com.harrypotter.designsystem.theme.CustomThemeResources
import com.harrypotter.designsystem.theme.Dimens
import com.harrypotter.features.characters.main.vm.model.CharacterUI

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen(
    characterUI: CharacterUI,
    onBackButtonClicked: () -> Unit,
) {
    Scaffold(
        topBar = { ToolbarCharacterDetail(onBackButtonClicked) },
    ) {
        CharacterDetailContent(characterUI)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolbarCharacterDetail(onBackButtonClicked: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.detail_screen_title),
                modifier = Modifier.testTag(CHARACTER_DETAIL_SCREEN_TOOLBAR_TEST_TAG),
            )
        },
        navigationIcon = {
            IconButton(onClick = { onBackButtonClicked() }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
            }
        },
    )
}

@Composable
fun CharacterDetailContent(characterUI: CharacterUI) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        NetworkImage(
            imageUrl = characterUI.imageUrl,
            modifier = Modifier
                .size(Dimens.dimen200)
                .clip(CircleShape),
        )
        Spacer(modifier = Modifier.size(Dimens.dimen16))
        CharacterDetailText(
            text = characterUI.name,
            style = CustomThemeResources.typography.headlineMedium,
            modifier = Modifier
                .fillMaxWidth()
                .testTag(CHARACTER_DETAIL_SCREEN_NAME_TEST_TAG),
        )
        Spacer(modifier = Modifier.size(Dimens.dimen16))
        CharacterDetailText(
            text = characterUI.house,
            style = CustomThemeResources.typography.headlineSmall,
            modifier = Modifier
                .fillMaxWidth()
                .testTag(CHARACTER_DETAIL_SCREEN_HOUSE_TEST_TAG),
        )
        Spacer(modifier = Modifier.size(Dimens.dimen16))
        CharacterDetailText(
            text = characterUI.actorName,
            style = CustomThemeResources.typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .testTag(CHARACTER_DETAIL_SCREEN_ACTOR_NAME_TEST_TAG),
        )
        Spacer(modifier = Modifier.size(Dimens.dimen16))
        CharacterDetailText(
            text = characterUI.gender,
            style = CustomThemeResources.typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .testTag(CHARACTER_DETAIL_SCREEN_GENDER_TEST_TAG),
        )
        Spacer(modifier = Modifier.size(Dimens.dimen16))
        CharacterDetailText(
            text = characterUI.species,
            style = CustomThemeResources.typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .testTag(CHARACTER_DETAIL_SCREEN_SPECIES_TEST_TAG),
        )
        Spacer(modifier = Modifier.size(Dimens.dimen16))
        CharacterDetailText(
            text = characterUI.birth,
            style = CustomThemeResources.typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .testTag(CHARACTER_DETAIL_SCREEN_BIRTHDAY_TEST_TAG),
        )
    }
}

@Composable
fun CharacterDetailText(
    text: String,
    style: TextStyle,
    modifier: Modifier,
) {
    Text(
        text = text,
        maxLines = MAX_LINES,
        overflow = TextOverflow.Ellipsis,
        textAlign = TextAlign.Center,
        style = style,
        modifier = modifier,
    )
}

@Composable
@Preview
fun CharacterDetailScreenPreview() {
    CharacterDetailScreen(
        characterUI = CharacterUI(
            id = "1",
            name = "Harry Potter",
            house = "Gryffindor",
            imageUrl = "http://hp-api.herokuapp.com/images/harry.jpg",
            actorName = "Daniel Radcliffe",
            gender = "Male",
            species = "Human",
            birth = "31-07-1980",
        ),
        onBackButtonClicked = {},
    )
}

private const val MAX_LINES = 1

@VisibleForTesting
const val CHARACTER_DETAIL_SCREEN_TOOLBAR_TEST_TAG = "CHARACTER_DETAIL_SCREEN_TOOLBAR_TEST_TAG"

@VisibleForTesting
const val CHARACTER_DETAIL_SCREEN_NAME_TEST_TAG = "CHARACTER_DETAIL_SCREEN_NAME_TEST_TAG"

@VisibleForTesting
const val CHARACTER_DETAIL_SCREEN_HOUSE_TEST_TAG = "CHARACTER_DETAIL_SCREEN_HOUSE_TEST_TAG"

@VisibleForTesting
const val CHARACTER_DETAIL_SCREEN_ACTOR_NAME_TEST_TAG =
    "CHARACTER_DETAIL_SCREEN_ACTOR_NAME_TEST_TAG"

@VisibleForTesting
const val CHARACTER_DETAIL_SCREEN_GENDER_TEST_TAG = "CHARACTER_DETAIL_SCREEN_GENDER_TEST_TAG"

@VisibleForTesting
const val CHARACTER_DETAIL_SCREEN_SPECIES_TEST_TAG = "CHARACTER_DETAIL_SCREEN_SPECIES_TEST_TAG"

@VisibleForTesting
const val CHARACTER_DETAIL_SCREEN_BIRTHDAY_TEST_TAG = "CHARACTER_DETAIL_SCREEN_BIRTHDAY_TEST_TAG"