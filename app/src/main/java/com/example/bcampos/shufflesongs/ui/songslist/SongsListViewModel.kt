package com.example.bcampos.shufflesongs.ui.songslist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class SongsListViewModel : ViewModel() {
    val name = MutableLiveData<String>()

    fun start() {
        name.value = "Bruno"
    }

    fun getName(): String {
        return "name"
    }


}
