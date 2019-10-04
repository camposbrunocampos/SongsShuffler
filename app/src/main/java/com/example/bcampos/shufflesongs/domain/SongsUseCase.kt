package com.example.bcampos.shufflesongs.domain

interface SongsUseCase {
    fun loadSongsList(): State<List<Song>>
}
