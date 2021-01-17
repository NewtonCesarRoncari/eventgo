package com.newton.eventgo.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.newton.eventgo.R

fun ImageView.loadImage(
    imageAddress: String,
    imageError: Int = R.drawable.image_error_load
) {
    Glide.with(this)
        .load(imageAddress)
        .error(imageError)
        .diskCacheStrategy(DiskCacheStrategy.NONE)
        .skipMemoryCache(true)
        .centerCrop()
        .into(this)
}