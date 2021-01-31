package com.app.mvvm_dagger2_rxjava_kotlin

import android.app.ProgressDialog
import android.content.Context
import android.os.Build
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatCallback
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.widget.ContentLoadingProgressBar
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.drawable.DrawableUtils


@RequiresApi(Build.VERSION_CODES.Q)
fun loader(context: Context): ContentLoadingProgressBar {
    return ContentLoadingProgressBar(context).apply {
        minHeight = 10
        maxHeight = 10
        show()
    }
}

fun ImageView.loadImage(uri: String) {
    val options = RequestOptions()
        .placeholder(R.drawable.ic_launcher_background)
        .error(R.drawable.ic_launcher_background)

    Glide.with(this.context)
        .setDefaultRequestOptions(options)
        .load(uri)
        .into(this)
}
