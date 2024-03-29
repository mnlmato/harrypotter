package com.harrypotter.features.characters.detail.ui.design

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
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
                modifier = Modifier.testTag(CharacterDetailScreenTag.TOOLBAR.value),
            )
        },
        navigationIcon = {
            IconButton(onClick = { onBackButtonClicked() }) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "")
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
            animationTime = 333,
            text = characterUI.name,
            style = CustomThemeResources.typography.headlineMedium,
            modifier = Modifier
                .fillMaxWidth()
                .testTag(CharacterDetailScreenTag.NAME.value),
        )

        Spacer(modifier = Modifier.size(Dimens.dimen16))
        CharacterDetailText(
            isVisible = isPossibleStartCascadeAnimation,
            animationTime = 1_000,
            text = characterUI.house,
            style = CustomThemeResources.typography.headlineSmall,
            modifier = Modifier
                .fillMaxWidth()
                .testTag(CharacterDetailScreenTag.HOUSE.value),
        )
        Spacer(modifier = Modifier.size(Dimens.dimen16))
        CharacterDetailText(
            isVisible = isPossibleStartCascadeAnimation,
            animationTime = 1_500,
            text = characterUI.actorName,
            style = CustomThemeResources.typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .testTag(CharacterDetailScreenTag.ACTOR.value),
        )
        Spacer(modifier = Modifier.size(Dimens.dimen16))
        CharacterDetailText(
            isVisible = isPossibleStartCascadeAnimation,
            animationTime = 2_000,
            text = characterUI.gender,
            style = CustomThemeResources.typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .testTag(CharacterDetailScreenTag.GENDER.value),
        )
        Spacer(modifier = Modifier.size(Dimens.dimen16))
        CharacterDetailText(
            isVisible = isPossibleStartCascadeAnimation,
            animationTime = 2_500,
            text = characterUI.species,
            style = CustomThemeResources.typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .testTag(CharacterDetailScreenTag.SPECIES.value),
        )
        Spacer(modifier = Modifier.size(Dimens.dimen16))
        CharacterDetailText(
            isVisible = isPossibleStartCascadeAnimation,
            animationTime = 3_000,
            text = characterUI.birth,
            style = CustomThemeResources.typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .testTag(CharacterDetailScreenTag.BIRTHDAY.value),
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
    animationTime: Int = 0,
    text: String,
    style: TextStyle,
    modifier: Modifier,
) {
    val alphaValue: Float by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        animationSpec = tween(animationTime)
    )
    Text(
        text = text,
        maxLines = MAX_LINES,
        overflow = TextOverflow.Ellipsis,
        textAlign = TextAlign.Center,
        style = style,
        modifier = modifier.alpha(alphaValue),
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

enum class CharacterDetailScreenTag(val value: String) {
    TOOLBAR("CHARACTER_DETAIL_SCREEN_TOOLBAR_TEST_TAG"),
    NAME("CHARACTER_DETAIL_SCREEN_NAME_TEST_TAG"),
    HOUSE("CHARACTER_DETAIL_SCREEN_HOUSE_TEST_TAG"),
    ACTOR("CHARACTER_DETAIL_SCREEN_ACTOR_NAME_TEST_TAG"),
    GENDER("CHARACTER_DETAIL_SCREEN_GENDER_TEST_TAG"),
    SPECIES("CHARACTER_DETAIL_SCREEN_SPECIES_TEST_TAG"),
    BIRTHDAY("CHARACTER_DETAIL_SCREEN_BIRTHDAY_TEST_TAG"),
}