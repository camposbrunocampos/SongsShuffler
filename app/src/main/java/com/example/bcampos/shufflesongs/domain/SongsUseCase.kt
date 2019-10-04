package com.example.bcampos.shufflesongs.domain

import com.example.bcampos.shufflesongs.data.State

interface SongsUseCase {
    fun loadSongsList(): State<List<Song>>
}
