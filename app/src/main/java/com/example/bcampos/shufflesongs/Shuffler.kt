package com.example.bcampos.shufflesongs

import com.example.bcampos.shufflesongs.domain.Song
import java.util.*

class Shuffler {
    companion object {
        fun shuffle(songs: List<Song>, isShuffled: Boolean = false): List<Song> {
            var songsList = songs
            if (!isShuffled) songsList = songs.shuffled()
            val duplicatedSongsStack = Stack<Song>()
            duplicatedSongsStack.addAll(getDuplicatedSongs(songsList))
            val noDuplicatesSongsList = removeDuplicatedSongs(songsList)
            return getMergedSongs(duplicatedSongsStack, noDuplicatesSongsList)
        }

        private fun getMergedSongs(
            duplicatedSongsStack: Stack<Song>,
            shuffledSongs: List<Song>
        ): List<Song> {
            val mergedSongs = shuffledSongs.toMutableList()
            while (!duplicatedSongsStack.empty()) {
                val song = duplicatedSongsStack.pop()
                insertSongAvoidingRepeating(mergedSongs, song)
            }
            return mergedSongs
        }

        private fun insertSongAvoidingRepeating(
            mergedSongs: MutableList<Song>,
            song: Song
        ) {
            for (songIndex in mergedSongs.indices) {
                if (songIndex + 1 == mergedSongs.count()
                    && !song.artistName.equals(mergedSongs[songIndex].artistName, true)
                ) {
                    mergedSongs.add(songIndex + 1, song)
                    break
                } else if (songIndex + 1 < mergedSongs.count()
                    && !song.artistName.equals(mergedSongs[songIndex].artistName, true)
                    && !song.artistName.equals(mergedSongs[songIndex + 1].artistName, true)
                ) {
                    mergedSongs.add(songIndex + 1, song)
                    break
                }
            }
        }

        fun removeDuplicatedSongs(shuffledSongs: List<Song>): List<Song> {
            val artistsHashSet = HashSet<String>()
            val noDuplicateSongsList = ArrayList<Song>()
            for (song in shuffledSongs) {
                if (!artistsHashSet.contains(song.artistName.toLowerCase())) {
                    artistsHashSet.add(song.artistName.toLowerCase())
                    noDuplicateSongsList.add(song)
                }
            }

            return noDuplicateSongsList
        }

        fun getDuplicatedSongs(shuffledSongs: List<Song>): List<Song> {
            val duplicatedSongs = ArrayList<Song>()
            val artistsHashSet = HashSet<String>()
            for (song in shuffledSongs) {
                if (artistsHashSet.contains(song.artistName.toLowerCase())) {
                    duplicatedSongs.add(song)
                }  else {
                    artistsHashSet.add(song.artistName.toLowerCase())
                }

            }

            return duplicatedSongs
        }

    }
}