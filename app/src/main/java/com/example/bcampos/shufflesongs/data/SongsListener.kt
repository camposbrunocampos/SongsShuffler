package com.example.bcampos.shufflesongs.data

import com.example.bcampos.shufflesongs.Song
import com.example.bcampos.shufflesongs.State

interface SongsListener {
    fun updateState(state: State<List<Song>>)
}
