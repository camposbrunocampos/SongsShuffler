package com.example.bcampos.shufflesongs.ui.songslist;

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bcampos.shufflesongs.domain.SongsUseCase
import kotlinx.coroutines.Dispatchers

class SongsViewModelFactory(private val songsUseCase: SongsUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SongsListViewModel::class.java)) {
            return SongsListViewModel(songsUseCase, Dispatchers.IO) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
