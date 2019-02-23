package com.example.bcampos.shufflesongs

import com.example.bcampos.shufflesongs.data.SongsListener
import com.example.bcampos.shufflesongs.domain.State
import com.example.bcampos.shufflesongs.domain.Song
import com.example.bcampos.shufflesongs.domain.SongsUseCase

class MockedSongsRepository: SongsUseCase {
    private var songsListener: SongsListener? = null
    var mockedResponse: State<List<Song>>? = null


    override fun loadSongsList() {
        songsListener?.updateState(mockedResponse!!)
    }

    override fun registerListener(listener: SongsListener) {
        songsListener = listener
    }

    override fun clearListener() {
        songsListener = null
    }
}