package com.newton.eventgo.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(
    imageAddress: String
) {
    Glide.with(this)
        .load(imageAddress)
        .centerCrop()
        .into(this)
}