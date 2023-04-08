package com.harrypotter.features.characters.detail.ui.design

import android.annotation.SuppressLint
import androidx.annotation.VisibleForTesting
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.drawablepainter.DrawablePainter
import com.harrypotter.R
import com.harrypotter.coreui.image.NetworkImageDrawable
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
        var isPossibleStartCascadeAnimation by rememberSaveable { mutableStateOf(false) }
        AvatarDetail(
            characterUI.imageUrl,
            isAvatarLoaded = {
                isPossibleStartCascadeAnimation = true
            },
        )
        Spacer(modifier = Modifier.size(Dimens.dimen16))
        CharacterDetailText(
            isVisible = isPossibleStartCascadeAnimation,
            enterTransitionTime = 333,
            text = characterUI.name,
            style = CustomThemeResources.typography.headlineMedium,
            modifier = Modifier
                .fillMaxWidth()
                .testTag(CHARACTER_DETAIL_SCREEN_NAME_TEST_TAG),
        )

        Spacer(modifier = Modifier.size(Dimens.dimen16))
        CharacterDetailText(
            isVisible = isPossibleStartCascadeAnimation,
            enterTransitionTime = 1_000,
            text = characterUI.house,
            style = CustomThemeResources.typography.headlineSmall,
            modifier = Modifier
                .fillMaxWidth()
                .testTag(CHARACTER_DETAIL_SCREEN_HOUSE_TEST_TAG),
        )
        Spacer(modifier = Modifier.size(Dimens.dimen16))
        CharacterDetailText(
            isVisible = isPossibleStartCascadeAnimation,
            enterTransitionTime = 1_500,
            text = characterUI.actorName,
            style = CustomThemeResources.typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .testTag(CHARACTER_DETAIL_SCREEN_ACTOR_NAME_TEST_TAG),
        )
        Spacer(modifier = Modifier.size(Dimens.dimen16))
        CharacterDetailText(
            isVisible = isPossibleStartCascadeAnimation,
            enterTransitionTime = 2_000,
            text = characterUI.gender,
            style = CustomThemeResources.typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .testTag(CHARACTER_DETAIL_SCREEN_GENDER_TEST_TAG),
        )
        Spacer(modifier = Modifier.size(Dimens.dimen16))
        CharacterDetailText(
            isVisible = isPossibleStartCascadeAnimation,
            enterTransitionTime = 2_500,
            text = characterUI.species,
            style = CustomThemeResources.typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .testTag(CHARACTER_DETAIL_SCREEN_SPECIES_TEST_TAG),
        )
        Spacer(modifier = Modifier.size(Dimens.dimen16))
        CharacterDetailText(
            isVisible = isPossibleStartCascadeAnimation,
            enterTransitionTime = 3_000,
            text = characterUI.birth,
            style = CustomThemeResources.typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .testTag(CHARACTER_DETAIL_SCREEN_BIRTHDAY_TEST_TAG),
        )
    }
}

@Composable
fun AvatarDetail(imageUrl: String, isAvatarLoaded: () -> Unit) {
    var avatarSmallSize by rememberSaveable { mutableStateOf(true) }
    val avatarSize by animateDpAsState(
        targetValue = if (avatarSmallSize) Dimens.dimen48 else Dimens.dimen200,
        animationSpec = tween(666),
        finishedListener = { isAvatarLoaded() },
    )

    NetworkImageDrawable(url = imageUrl) { drawable ->
        Image(
            painter = DrawablePainter(drawable),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(avatarSize)
                .clip(CircleShape),
        )
        avatarSmallSize = false
    }
}

@Composable
fun CharacterDetailText(
    isVisible: Boolean = false,
    enterTransitionTime: Int = 0,
    text: String,
    style: TextStyle,
    modifier: Modifier,
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(animationSpec = tween(enterTransitionTime)),
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

    // Invisible skeleton to get the animation result
    if (!isVisible) {
        Text(
            text = text,
            color = Color.Transparent,
            style = style,
        )
    }
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