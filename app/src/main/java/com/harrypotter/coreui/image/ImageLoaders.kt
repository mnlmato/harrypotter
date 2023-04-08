package com.harrypotter.coreui.image

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
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
    onResourceLoaded: (() -> Unit)? = null,
    @DrawableRes errorPlaceholder: Int = R.drawable.ic_baseline_image_not_supported_24,
) {
    var isCallbackEmitted by rememberSaveable { mutableStateOf(false) }

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
            requestBuilder
                .error(errorDrawablePlaceholder)
                .addListener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        if (!isCallbackEmitted) {
                            onResourceLoaded?.invoke()
                            isCallbackEmitted = true
                        }
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        if (!isCallbackEmitted) {
                            onResourceLoaded?.invoke()
                            isCallbackEmitted = true
                        }
                        return false
                    }
                })
        }
    )
}

/**
 *  This loader convert a Url in a Drawable
 * */
@Composable
fun NetworkImageDrawable(
    url: String,
    @DrawableRes errorPlaceholder: Int = R.drawable.ic_baseline_image_not_supported_24,
    content: @Composable (Drawable) -> Unit,
) {
    var drawable by remember { mutableStateOf<Drawable?>(null) }
    val context = LocalContext.current
    val color = LocalContentColor.current
    val errorDrawablePlaceholder = remember {
        val originalDrawable = ContextCompat.getDrawable(context, errorPlaceholder)
        originalDrawable!!.mutate()
        originalDrawable.colorFilter = PorterDuffColorFilter(color.toArgb(), PorterDuff.Mode.SRC_IN)
        originalDrawable
    }
    drawable?.let { content(it) }

    DisposableEffect(url) {
        val requestManager = Glide.with(context)
        val target = object : CustomTarget<Drawable>() {
            override fun onResourceReady(
                resource: Drawable,
                transition: Transition<in Drawable>?
            ) {
                drawable = resource
            }

            override fun onLoadCleared(placeholder: Drawable?) {
                drawable = null
            }

            override fun onLoadFailed(errorDrawable: Drawable?) {
                drawable = errorDrawablePlaceholder
            }
        }
        requestManager.load(url).into(target)


        onDispose {
            requestManager.clear(target)
        }
    }
}