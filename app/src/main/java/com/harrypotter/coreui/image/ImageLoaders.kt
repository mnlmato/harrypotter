package com.harrypotter.coreui.image

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import androidx.annotation.DrawableRes
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.harrypotter.R

/**
 * @param modifier should contains the size
 **/
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NetworkImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    contentDescription: String = "",
    @DrawableRes errorPlaceholder: Int = R.drawable.ic_baseline_image_not_supported_24,
) {
    val context = LocalContext.current
    val color = LocalContentColor.current
    val errorDrawablePlaceholder = remember {
        val originalDrawable = ContextCompat.getDrawable(context, errorPlaceholder)
        originalDrawable!!.mutate()
        originalDrawable.colorFilter = PorterDuffColorFilter(color.toArgb(), PorterDuff.Mode.SRC_IN)
        originalDrawable
    }

    GlideImage(
        model = imageUrl,
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = modifier,
        requestBuilderTransform = { requestBuilder ->
            requestBuilder.error(errorDrawablePlaceholder)
        }
    )
}