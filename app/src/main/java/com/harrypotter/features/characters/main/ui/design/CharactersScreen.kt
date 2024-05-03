package com.harrypotter.features.characters.main.ui.design

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.harrypotter.R
import com.harrypotter.coreui.errors.GenericErrorScreen
import com.harrypotter.coreui.image.NetworkImage
import com.harrypotter.designsystem.components.loading.LoadingCustom
import com.harrypotter.designsystem.theme.CustomShape
import com.harrypotter.designsystem.theme.CustomThemeResources
import com.harrypotter.designsystem.theme.Dimens
import com.harrypotter.features.characters.main.vm.model.CharacterUI
import com.harrypotter.features.characters.main.vm.model.CharactersActionsFromUI
import com.harrypotter.features.characters.main.vm.model.CharactersListUI
import com.harrypotter.features.characters.main.vm.model.CharactersState

@Composable
fun CharactersScreen(
    charactersState: CharactersState,
    onClickAction: (CharactersActionsFromUI) -> Unit,
) {
    Scaffold(
        topBar = { ToolbarCharacters() },
    ) {
        when (charactersState) {
            is CharactersState.UI.Loading -> {
                LoadingCustom(Modifier.fillMaxSize())
            }

            is CharactersState.UI.Error -> {
                GenericErrorScreen(onRetryButtonClickListener = {
                    onClickAction(CharactersActionsFromUI.RetryButtonClick)
                })
            }

            is CharactersState.UI.Success -> {
                CharactersList(
                    charactersList = charactersState.characters,
                    modifier = Modifier.padding(paddingValues = it),
                    onClickAction = onClickAction,
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolbarCharacters() {
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.app_name))
        },
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CharactersList(
    charactersList: CharactersListUI,
    modifier: Modifier,
    onClickAction: (CharactersActionsFromUI) -> Unit,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .testTag(CharactersListScreenTag.LIST.value),
    ) {
        charactersList.list.forEach { (headerHouse, characters) ->
            stickyHeader {
                StickyHeader(
                    title = headerHouse,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(enabled = false) {},
                )
            }

            items(characters) {
                CharacterItemRow(
                    characterUI = it,
                    onClickAction = onClickAction,
                    Modifier.padding(all = Dimens.dimen4),
                )
            }
        }
    }
}

@Composable
fun StickyHeader(title: String, modifier: Modifier) {
    Text(
        text = title,
        textAlign = TextAlign.Center,
        style = CustomThemeResources.typography.titleMedium,
        maxLines = MAX_LINES,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier
            .background(CustomThemeResources.colors.tertiary)
            .size(Dimens.dimen48)
            .wrapContentSize(Alignment.Center),
    )
}

@Composable
fun CharacterItemRow(
    characterUI: CharacterUI,
    onClickAction: (CharactersActionsFromUI) -> Unit,
    modifier: Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .height(Dimens.dimen96)
            .clip(shape = CustomShape.types.extraLarge)
            .clickable { onClickAction(CharactersActionsFromUI.ItemListClick(characterUI)) }
    ) {
        Spacer(modifier = Modifier.padding(horizontal = Dimens.dimen8))
        NetworkImage(
            imageUrl = characterUI.imageUrl,
            modifier = Modifier
                .size(Dimens.dimen48)
                .clip(CircleShape),
        )
        Spacer(modifier = Modifier.padding(Dimens.dimen8))
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = characterUI.name,
                style = CustomThemeResources.typography.titleSmall,
                maxLines = MAX_LINES,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.padding(all = Dimens.dimen2))
            Text(
                text = characterUI.house,
                style = CustomThemeResources.typography.labelSmall,
                maxLines = MAX_LINES,
                overflow = TextOverflow.Ellipsis
            )
        }
        Spacer(modifier = Modifier.padding(Dimens.dimen8))
    }
}

@Composable
@Preview
fun CharacterDetailScreenPreview() {
    val successData = listOf(
        CharacterUI(
            id = "1",
            name = "Harry Potter Harry Potter Harry Potter Harry Potter Harry Potter",
            house = "Gryffindor",
            imageUrl = "http://hp-api.herokuapp.com/images/harry.jpg",
            actorName = "Daniel Radcliffe",
            gender = "Male",
            species = "Human",
            birth = "31-07-1980",
        ),
        CharacterUI(
            id = "1",
            name = "Harry Potter Harry Potter Harry Potter Harry Potter Harry Potter",
            house = "Gryffindor",
            imageUrl = "http://hp-api.herokuapp.com/images/harry.jpg",
            actorName = "Daniel Radcliffe",
            gender = "Male",
            species = "Human",
            birth = "31-07-1980",
        ),
    ).groupBy { it.house }
    CharactersScreen(CharactersState.UI.Success(CharactersListUI(successData)), {})
}

private const val MAX_LINES = 1

enum class CharactersListScreenTag(val value: String) {
    LIST("CHARACTERS_SCREEN_LIST_TEST_TAG"),
}