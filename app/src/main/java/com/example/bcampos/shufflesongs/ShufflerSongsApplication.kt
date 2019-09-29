package com.example.bcampos.shufflesongs

import android.app.Application
import com.example.bcampos.shufflesongs.data.SongsRepository
import com.example.bcampos.shufflesongs.framework.SongsAPISource
import com.example.bcampos.shufflesongs.ui.songslist.SongsViewModelFactory
import com.example.bcampos.shufflesongs.ui.songslist.utils.ImageLoader
import com.example.bcampos.shufflesongs.ui.songslist.utils.ImageLoaderImpl
import okhttp3.OkHttpClient

class ShufflerSongsApplication: Application(), DependencyManager {
    override fun getImageLoader(): ImageLoader {
        return imageLoader
    }

    override fun getSongViewModelFactory(): SongsViewModelFactory {
        return songsViewModelFactory
    }

    private lateinit var songsViewModelFactory: SongsViewModelFactory
    private lateinit var imageLoader: ImageLoader

    override fun onCreate() {
        super.onCreate()
        songsViewModelFactory = SongsViewModelFactory(SongsRepository(SongsAPISource()))
        imageLoader = ImageLoaderImpl()
    }

}