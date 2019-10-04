package com.example.bcampos.shufflesongs

import com.example.bcampos.shufflesongs.domain.Song
import com.example.bcampos.shufflesongs.usecases.Shuffler
import org.junit.Assert
import org.junit.Test

class ShufflerTest {

    @Test
    fun shouldShuffleAvoidingDuplicatedArtistsTogether() {
        val shuffledSongs = listOf(
            Song("Ed Sheraan", "love"),
            Song("red hot", "under the bridge"),
            Song("red hot", "the zephyr song"),
            Song("red hot", "fly song"),
            Song("Ed Sheraan", "shape of you")
        )

        val expectedShuffledSongs = listOf(
            Song("Ed Sheraan", "love"),
            Song("red hot", "under the bridge"),
            Song("Ed Sheraan", "shape of you"),
            Song("red hot", "fly song")
        )

        Assert.assertEquals(expectedShuffledSongs, Shuffler.shuffle(shuffledSongs, true))
    }

    @Test
    fun shouldShuffleAvoidingDuplicatedArtistsTogether_fromBigList() {
        val shuffledSongs = listOf(
            Song("Ed Sheraan", "love"),
            Song("red hot", "under the bridge"),
            Song("red hot", "the zephyr song"),
            Song("red hot", "fly song"),
            Song("MC kevin", "samba mucosa"),
            Song("MC Don juan", "hoje"),
            Song("MC Don juan", "sarrei"),
            Song("Ed Sheraan", "shape of you")
        )

        val expectedShuffledSongs = listOf(
            Song("Ed Sheraan", "love"),
            Song("red hot", "fly song"),
            Song("MC Don juan", "sarrei"),
            Song("red hot", "under the bridge"),
            Song("Ed Sheraan", "shape of you"),
            Song("red hot", "the zephyr song"),
            Song("MC kevin", "samba mucosa"),
            Song("MC Don juan", "hoje")
        )

        Assert.assertEquals(expectedShuffledSongs, Shuffler.shuffle(shuffledSongs, true))
    }

    @Test
    fun shouldRemoveDuplicatedArtistsFromSongs() {
        val shuffledSongs = listOf(
            Song("Ed Sheraan", "love"),
            Song("red hot", "under the bridge"),
            Song("red hot", "the zephyr song"),
            Song("red hot", "fly song"),
            Song("Ed Sheraan", "shape of you")
        )

        val expectedSongs = listOf(
            Song("Ed Sheraan", "love"),
            Song("red hot", "under the bridge")
        )

        Assert.assertEquals(expectedSongs, Shuffler.removeDuplicatedSongs(shuffledSongs))

    }

    @Test
    fun shouldGetDuplicatedArtistsSongs() {
        val duplicatedSongs = listOf(
            Song("Ed Sheraan", "love"),
            Song("red hot", "under the bridge"),
            Song("red hot", "the zephyr song"),
            Song("Ed Sheraan", "shape of you")
        )

        val expectedDuplicatedSongs = listOf(
            Song("red hot", "the zephyr song"),
            Song("Ed Sheraan", "shape of you")
        )

        Assert.assertEquals(Shuffler.getDuplicatedSongs(duplicatedSongs), expectedDuplicatedSongs)
    }
}