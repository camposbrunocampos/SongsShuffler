package com.example.bcampos.shufflesongs.mock

import android.app.Application
import android.widget.ImageView
import com.example.bcampos.shufflesongs.DependencyManager
import com.example.bcampos.shufflesongs.ui.songslist.SongsViewModelFactory
import com.example.bcampos.shufflesongs.ui.songslist.utils.ImageLoader

class MockShufflerSongsApplication : Application(), DependencyManager {
    override fun getSongViewModelFactory(): SongsViewModelFactory {
        return songsViewModelFactory
    }

    lateinit var songsViewModelFactory: SongsViewModelFactory

    override fun getImageLoader(): ImageLoader {
        return fakeImageLoader()
    }

    class fakeImageLoader : ImageLoader {
        override fun load(view: ImageView, url: String) {
            //do nothing - no need of testing picasso image load for tests
        }

    }
}