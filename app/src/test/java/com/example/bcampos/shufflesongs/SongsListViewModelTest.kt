package com.example.bcampos.shufflesongs

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.bcampos.shufflesongs.data.SongsRepository
import com.example.bcampos.shufflesongs.data.State
import com.example.bcampos.shufflesongs.domain.Song
import com.example.bcampos.shufflesongs.mock.MockedSongsSource
import com.example.bcampos.shufflesongs.ui.songslist.SongsListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SongsListViewModelTest {

    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testCoroutineDispatcher)
    }


    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun shouldLoadSongsWithError() {
        val songsUseCase = SongsRepository(
            MockedSongsSource(
                State(
                    State.Name.ERROR,
                    emptyList()
                )
            )
        )
        val viewModel = SongsListViewModel(songsUseCase, testCoroutineDispatcher)
        viewModel.loadSongs()


        Assert.assertEquals(
            viewModel.songsState.value,
            State(
                State.Name.ERROR,
                ArrayList<Song>()
            )
        )
    }

    @Test
    fun shouldLoadSongsWithSuccess() {

        val mockedSongsList = listOf(Song("bla", "bla"))
        val songsRepository = SongsRepository(
            MockedSongsSource(
                State(
                    State.Name.LOADED,
                    mockedSongsList
                )
            )
        )
        val viewModel = SongsListViewModel(songsRepository, testCoroutineDispatcher)

        viewModel.loadSongs()

        Assert.assertEquals(
            viewModel.songsState.value,
            State(
                State.Name.LOADED,
                mockedSongsList
            )
        )
    }

    @Test
    fun shouldStayInLoadingStateWhileLoadingSongs() {
        val songsRepository = SongsRepository(
            MockedSongsSource(
                State(
                    State.Name.LOADING,
                    listOf()
                )
            )
        )
        val viewModel = SongsListViewModel(songsRepository, testCoroutineDispatcher)
        viewModel.loadSongs()

        Assert.assertEquals(
            viewModel.songsState.value,
            State(
                State.Name.LOADING,
                ArrayList<Song>()
            )
        )
    }

}