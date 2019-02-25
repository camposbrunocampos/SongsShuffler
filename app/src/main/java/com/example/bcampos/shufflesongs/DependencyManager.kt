package com.example.bcampos.shufflesongs

import com.example.bcampos.shufflesongs.ui.songslist.SongsViewModelFactory
import com.example.bcampos.shufflesongs.ui.songslist.utils.ImageLoader

interface DependencyManager {
    fun getSongViewModelFactory(): SongsViewModelFactory
    fun getImageLoader(): ImageLoader
}
