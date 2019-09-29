package com.example.bcampos.shufflesongs.data

import com.example.bcampos.shufflesongs.domain.Song
import com.example.bcampos.shufflesongs.domain.SongsUseCase
import com.example.bcampos.shufflesongs.domain.State


class SongsRepository(
    private val songsSource: SongsSource
) : SongsUseCase {

    private var songsListener: SongsListener? = null

    override fun loadSongsList() {
        val songResponse= songsSource.getSongs()
        songsListener?.updateState(songResponse)
    }

    override fun registerListener(listener: SongsListener) {
        songsListener = listener
        songsListener?.updateState(State(State.Name.IDLE))
    }

    override fun clearListener() {
        songsListener = null
    }

    interface SongsSource {
        fun getSongs() : State<List<Song>>
    }
}