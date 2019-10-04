package com.example.bcampos.shufflesongs.ui.songslist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bcampos.shufflesongs.data.State
import com.example.bcampos.shufflesongs.domain.Shuffler
import com.example.bcampos.shufflesongs.domain.Song
import com.example.bcampos.shufflesongs.domain.SongsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class SongsListViewModel(private val songsUseCase: SongsUseCase, override val coroutineContext: CoroutineContext) :
    ViewModel(), CoroutineScope {

    val songsState: MutableLiveData<State<List<Song>>> by lazy {
        MutableLiveData<State<List<Song>>>()
    }

    private val job = Job()

    fun loadSongs() {
        launch {
            songsState.postValue(
                State(
                    State.Name.LOADING,
                    emptyList()
                )
            )
            val songsResponse = songsUseCase.loadSongsList()
            songsState.postValue(songsResponse)
        }
    }

    override fun onCleared() {
        job.cancel()
        super.onCleared()
    }

    fun shuffleSongs() {
        songsState.value = State(
            State.Name.LOADING,
            songsState.value?.value
        )
        launch {
            delay(1000)
            val shuffledSongs = Shuffler.shuffle(songsState.value?.value!!, false)
            songsState.postValue(
                State(
                    State.Name.LOADED,
                    shuffledSongs
                )
            )
        }
    }
}
