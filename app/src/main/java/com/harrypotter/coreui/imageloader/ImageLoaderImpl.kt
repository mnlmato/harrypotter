package com.harrypotter.coreui.imageloader

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.harrypotter.R
import javax.inject.Inject

class ImageLoaderImpl @Inject constructor(): ImageLoader {

    override fun loadImage(imageView: ImageView, url: String) {
        Glide.with(imageView.context)
            .load(url)
            .circleCrop()
            .placeholder(R.drawable.ic_baseline_image_not_supported_24)
            .into(imageView)
    }
}