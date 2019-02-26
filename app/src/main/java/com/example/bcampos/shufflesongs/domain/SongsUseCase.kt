package com.example.bcampos.shufflesongs.domain

import com.example.bcampos.shufflesongs.data.SongsListener

interface SongsUseCase {
    fun loadSongsList()
    fun registerListener(listener: SongsListener)
    fun clearListener()
}
