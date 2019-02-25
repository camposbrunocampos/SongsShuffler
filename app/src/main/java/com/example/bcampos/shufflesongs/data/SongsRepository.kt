package com.example.bcampos.shufflesongs.data

import android.util.Log
import com.example.bcampos.shufflesongs.domain.Song
import com.example.bcampos.shufflesongs.domain.SongsUseCase
import com.example.bcampos.shufflesongs.domain.State
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.*
import org.json.JSONObject
import java.io.IOException


class SongsRepository(
    private val httpClient: OkHttpClient
) : SongsUseCase {

    var songsListener: SongsListener? = null

    override fun loadSongsList() {
        val request = Request.Builder()
            .url("https://us-central1-tw-exercicio-mobile.cloudfunctions.net/lookup?id=909253,1171421960,358714030")
            .build()
        songsListener?.updateState(State(State.Name.LOADING, emptyList()))

        httpClient.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e(SongsRepository::class.java.toString(), "onFailure" + e.message)
                songsListener?.updateState(State(State.Name.ERROR, emptyList()))
            }

            override fun onResponse(call: Call, response: Response) {
                try {
                    val json = response.body()!!.string()
                    val jsonObject = JSONObject(json)
                    val resultsJson = jsonObject.getString("results")

                    val songType = object : TypeToken<List<Song>>() {}.type
                    val songsList = Gson().fromJson<List<Song>>(resultsJson, songType)
                    val filteredSongsList = filterSongsWithType(songsList, "track")

                    songsListener?.updateState(State(State.Name.LOADED, filteredSongsList))
                } catch (e: Exception) {
                    Log.e(SongsRepository::class.java.toString(), "Error" + e.message)
                    songsListener?.updateState(State(State.Name.ERROR, emptyList()))
                }
            }
        })
    }

    override fun registerListener(listener: SongsListener) {
        songsListener = listener
        songsListener?.updateState(State(State.Name.IDLE))
    }

    override fun clearListener() {
        songsListener = null
    }

    fun filterSongsWithType(songs: List<Song>, filterType: String): List<Song> {
        return songs.filter { it.wrapperType == filterType }
    }
}