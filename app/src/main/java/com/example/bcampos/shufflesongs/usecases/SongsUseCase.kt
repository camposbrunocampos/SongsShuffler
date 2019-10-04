package com.example.bcampos.shufflesongs.usecases

import com.example.bcampos.shufflesongs.data.State
import com.example.bcampos.shufflesongs.domain.Song

interface SongsUseCase {
    fun loadSongsList(): State<List<Song>>
}
