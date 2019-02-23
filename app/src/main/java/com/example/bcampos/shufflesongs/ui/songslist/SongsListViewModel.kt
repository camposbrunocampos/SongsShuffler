package com.example.bcampos.shufflesongs.ui.songslist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bcampos.shufflesongs.domain.Song
import com.example.bcampos.shufflesongs.domain.State
import com.example.bcampos.shufflesongs.data.SongsListener
import com.example.bcampos.shufflesongs.domain.SongsUseCase


class SongsListViewModel(private val songsUseCase: SongsUseCase) : ViewModel(), SongsListener {

    override fun updateState(state: State<List<Song>>) {
        songsState.postValue(state)
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
