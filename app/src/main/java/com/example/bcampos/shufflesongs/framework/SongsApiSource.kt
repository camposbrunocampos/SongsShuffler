package com.example.bcampos.shufflesongs.framework

import android.util.Log
import com.example.bcampos.shufflesongs.data.SongsRepository
import com.example.bcampos.shufflesongs.data.State
import com.example.bcampos.shufflesongs.domain.Song
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

class SongsApiSource : SongsRepository.SongsSource {
    override fun getSongs(): State<List<Song>> {
        val request = Request.Builder()
            .url("https://us-central1-tw-exercicio-mobile.cloudfunctions.net/lookup?id=909253,1171421960,358714030")
            .build()
        val httpClient = OkHttpClient()
        val songsResponse = httpClient.newCall(request).execute()
        if (songsResponse.isSuccessful) {
            try {
                val json = songsResponse.body()!!.string()
                val songsList = parseSongs(json)
                val filteredSongsList = filterSongsWithType(songsList, "track")

                return State(
                    State.Name.LOADED,
                    filteredSongsList
                )
            } catch (e: Exception) {
                Log.e(SongsApiSource::class.java.toString(), "Error" + e.message)
                return State(
                    State.Name.ERROR,
                    emptyList()
                )
            }
        } else {
            Log.e(SongsApiSource::class.java.toString(), "onFailure" + songsResponse.code())
            return State(
                State.Name.ERROR,
                emptyList()
            )
        }
    }

    private fun parseSongs(json: String): List<Song> {
        val jsonObject = JSONObject(json)
        val resultsJson = jsonObject.getString("results")

        val songType = object : TypeToken<List<Song>>() {}.type
        return Gson().fromJson<List<Song>>(resultsJson, songType)
    }

    fun filterSongsWithType(songs: List<Song>, filterType: String): List<Song> {
        return songs.filter { it.wrapperType == filterType }
    }
}