package com.example.bcampos.shufflesongs.mock

import com.example.bcampos.shufflesongs.data.SongsRepository
import com.example.bcampos.shufflesongs.domain.Song
import com.example.bcampos.shufflesongs.domain.State

class MockedSongsSource(private val mockedResponse: State<List<Song>>) : SongsRepository.SongsSource {
    override fun getSongs(): State<List<Song>> {
        return mockedResponse
    }
}