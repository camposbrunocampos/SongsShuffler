package com.example.bcampos.shufflesongs.ui.songslist;

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bcampos.shufflesongs.domain.SongsUseCase

class SongsViewModelFactory(private val songsUseCase: SongsUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SongsListViewModel::class.java)) {
            return SongsListViewModel(songsUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
