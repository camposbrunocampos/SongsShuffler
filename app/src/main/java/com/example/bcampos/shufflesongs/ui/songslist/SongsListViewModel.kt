package com.example.bcampos.shufflesongs.ui.songslist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bcampos.shufflesongs.Song
import com.example.bcampos.shufflesongs.State
import com.example.bcampos.shufflesongs.data.SongsListener
import com.example.bcampos.shufflesongs.domain.SongsUseCase


class SongsListViewModel(val songsUseCase: SongsUseCase) : ViewModel(), SongsListener {

    override fun updateState(state: State<List<Song>>) {
        songsState.value = state
    }

    val songsState: MutableLiveData<State<List<Song>>> by lazy {
        MutableLiveData<State<List<Song>>>()
    }



    fun loadSongs() {
        songsUseCase.registerListener(this)
        songsUseCase.loadSongsList()
    }



    fun getName(): String {
        return "songsState"
    }


}
