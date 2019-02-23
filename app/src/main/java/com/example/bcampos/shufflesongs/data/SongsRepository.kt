package com.example.bcampos.shufflesongs.data

import com.example.bcampos.shufflesongs.domain.SongsUseCase

class SongsRepository: SongsUseCase {

    var songsListener: SongsListener? = null

    override fun loadSongsList() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun registerListener(listener: SongsListener) {
        songsListener = listener
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clearListener() {
        songsListener = null
    }
}