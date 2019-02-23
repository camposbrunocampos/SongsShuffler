package com.example.bcampos.shufflesongs

import com.example.bcampos.shufflesongs.data.SongsRepository
import com.example.bcampos.shufflesongs.domain.Song
import com.nhaarman.mockito_kotlin.mock
import okhttp3.OkHttpClient
import org.junit.Assert
import org.junit.Test

class SongsRepositoryTest {

    @Test
    fun shouldFilterSongs_withTrackType() {
        val mockHttpClient = mock<OkHttpClient>()
        val songsRepository = SongsRepository(mockHttpClient)
        val mockedResponse = listOf(
            Song("Ed Sheraan", "love", "track"),
            Song("red hot", "under the bridge", "artist"),
            Song("red hot", "the zephyr song", "track"),
            Song("red hot", "fly song", "artist"),
            Song("Ed Sheraan", "shape of you", "track")
        )
        val filteredSongs = songsRepository.filterSongsWithType(mockedResponse, "track")

        Assert.assertEquals(filteredSongs, listOf(Song("Ed Sheraan", "love", "track"),
            Song("red hot", "the zephyr song", "track"),
            Song("Ed Sheraan", "shape of you", "track")))
    }
}