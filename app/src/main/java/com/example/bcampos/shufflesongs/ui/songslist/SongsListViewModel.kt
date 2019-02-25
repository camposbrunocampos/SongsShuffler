package com.example.bcampos.shufflesongs.ui.songslist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bcampos.shufflesongs.Shuffler
import com.example.bcampos.shufflesongs.data.SongsListener
import com.example.bcampos.shufflesongs.domain.Song
import com.example.bcampos.shufflesongs.domain.SongsUseCase
import com.example.bcampos.shufflesongs.domain.State
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


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

    fun shuffleSongs() {
        songsState.value = State(State.Name.LOADING, songsState.value?.value)
        GlobalScope.launch {
            delay(1000)
            val shuffledSongs = Shuffler.shuffle(songsState.value?.value!!, false)
            songsState.postValue(State(State.Name.LOADED,shuffledSongs))
        }

    }
}
