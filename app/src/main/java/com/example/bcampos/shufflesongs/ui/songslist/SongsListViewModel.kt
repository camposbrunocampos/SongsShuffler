package com.example.bcampos.shufflesongs.ui.songslist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bcampos.shufflesongs.State


class SongsListViewModel : ViewModel() {
    val songsState = MutableLiveData<State<String>>()

    fun start() {
        songsState.value = State(State.Name.IDLE, "bruno")
    }

    fun getName(): String {
        return "songsState"
    }


}
