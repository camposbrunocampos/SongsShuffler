package com.example.bcampos.shufflesongs

import androidx.test.runner.AndroidJUnit4
import com.example.bcampos.shufflesongs.domain.State
import com.example.bcampos.shufflesongs.domain.Song
import com.example.bcampos.shufflesongs.mock.MockedSongsRepository
import com.example.bcampos.shufflesongs.ui.songslist.SongsListViewModel
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SongsListViewModelTest {

    @Test
    fun shouldLoadSongsWithError() {
        val songsUseCase = MockedSongsRepository()
        val viewModel = SongsListViewModel(songsUseCase)
        songsUseCase.registerListener(viewModel)
        songsUseCase.mockedResponse = State(
            State.Name.ERROR,
            emptyList()
        )
        songsUseCase.loadSongsList()

        Assert.assertEquals(viewModel.songsState.value,
            State(
                State.Name.ERROR,
                ArrayList<Song>()
            )
        )
    }

    @Test
    fun shouldLoadSongsWithSuccess() {
        val songsUseCase = MockedSongsRepository()
        val viewModel = SongsListViewModel(songsUseCase)
        songsUseCase.registerListener(viewModel)
        val mockedSongsList = listOf(Song("bla", "bla"))
        songsUseCase.mockedResponse = State(
            State.Name.LOADED,
            mockedSongsList
        )
        songsUseCase.loadSongsList()

        Assert.assertEquals(viewModel.songsState.value,
            State(
                State.Name.LOADED,
                mockedSongsList
            )
        )
    }

    @Test
    fun shouldStayInLoadingStateWhileLoadingSongs() {
        val songsUseCase = MockedSongsRepository()
        val viewModel = SongsListViewModel(songsUseCase)
        songsUseCase.registerListener(viewModel)
        songsUseCase.mockedResponse = State(
            State.Name.LOADING,
            listOf()
        )
        songsUseCase.loadSongsList()

        Assert.assertEquals(viewModel.songsState.value,
            State(
                State.Name.LOADING,
                ArrayList<Song>()
            )
        )
    }

}