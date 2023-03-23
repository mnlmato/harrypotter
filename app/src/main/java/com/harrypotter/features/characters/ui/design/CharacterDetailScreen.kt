package com.harrypotter.features.characters.ui.design

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.harrypotter.R
import com.harrypotter.designsystem.theme.CustomTypography
import com.harrypotter.features.characters.vm.model.CharacterUI

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
            Text(text = stringResource(id = R.string.detail_screen_title))
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
        CharacterAvatar(characterUI.imageUrl)
        Spacer(modifier = Modifier.size(16.dp))
        CharacterDetailText(
            text = characterUI.name,
            style = CustomTypography.types.headlineMedium,
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.size(16.dp))
        CharacterDetailText(
            text = characterUI.house,
            style = CustomTypography.types.headlineSmall,
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.size(16.dp))
        CharacterDetailText(
            text = characterUI.actorName,
            style = CustomTypography.types.bodyLarge,
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.size(16.dp))
        CharacterDetailText(
            text = characterUI.gender,
            style = CustomTypography.types.bodyLarge,
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.size(16.dp))
        CharacterDetailText(
            text = characterUI.species,
            style = CustomTypography.types.bodyLarge,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(16.dp))
        CharacterDetailText(
            text = characterUI.birth,
            style = CustomTypography.types.bodyLarge,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CharacterAvatar(imageUrl: String) {
    GlideImage(
        model = imageUrl,
        contentDescription = "Avatar",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .clip(CircleShape)
            .size(200.dp),
    )
}

@Composable
fun CharacterDetailText(text: String, style: TextStyle, modifier: Modifier) {
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
            name = "Harry Potter",
            house = "Gryffindor",
            imageUrl = "http://hp-api.herokuapp.com/images/harry.jpg",
            actorName = "Daniel Radcliffe",
            gender = "Male",
            species = "Human",
            birth = "31-07-1980"
        ),
        onBackButtonClicked = {},
    )
}

private const val MAX_LINES = 1