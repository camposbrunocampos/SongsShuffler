package com.example.bcampos.shufflesongs.data

import com.example.bcampos.shufflesongs.domain.Song
import com.example.bcampos.shufflesongs.domain.State

interface SongsListener {
    fun updateState(state: State<List<Song>>)
}
