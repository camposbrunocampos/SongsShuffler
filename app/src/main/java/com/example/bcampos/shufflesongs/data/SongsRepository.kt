package com.example.bcampos.shufflesongs.data

import com.example.bcampos.shufflesongs.domain.Song
import com.example.bcampos.shufflesongs.usecases.SongsUseCase


class SongsRepository(
    private val songsSource: SongsSource
) : SongsUseCase {

    override fun loadSongsList(): State<List<Song>> {
        return songsSource.getSongs()
    }

    interface SongsSource {
        fun getSongs(): State<List<Song>>
    }
}