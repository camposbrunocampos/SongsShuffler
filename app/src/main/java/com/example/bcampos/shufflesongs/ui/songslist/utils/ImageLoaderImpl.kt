package com.example.bcampos.shufflesongs.ui.songslist.utils

import android.widget.ImageView
import com.squareup.picasso.Picasso

class ImageLoaderImpl: ImageLoader {
    override fun load(view: ImageView, url: String) {
        Picasso.get().load(url).transform(CircleImageTransformation()).into(view)
    }
}
