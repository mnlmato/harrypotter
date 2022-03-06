package com.harrypotter.coreui.imageloader

import android.widget.ImageView

interface ImageLoader {
    fun loadImage(imageView: ImageView, url: String)
}