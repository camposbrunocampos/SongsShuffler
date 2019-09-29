package com.example.bcampos.shufflesongs

import com.example.bcampos.shufflesongs.domain.Song
import com.example.bcampos.shufflesongs.framework.SongsAPISource
import org.junit.Assert
import org.junit.Test

class SongsApiSourceTest {

    @Test
    fun shouldFilterSongs_withTrackType() {
        val songsApiSource = SongsAPISource()
        val mockedResponse = listOf(
            Song("Ed Sheraan", "love", "track"),
            Song("red hot", "under the bridge", "artist"),
            Song("red hot", "the zephyr song", "track"),
            Song("red hot", "fly song", "artist"),
            Song("Ed Sheraan", "shape of you", "track")
        )
        val filteredSongs = songsApiSource.filterSongsWithType(mockedResponse, "track")

        Assert.assertEquals(
            filteredSongs, listOf(
                Song("Ed Sheraan", "love", "track"),
                Song("red hot", "the zephyr song", "track"),
                Song("Ed Sheraan", "shape of you", "track")
            )
        )
    }
}