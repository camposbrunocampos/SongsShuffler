package com.example.bcampos.shufflesongs.ui.songslist

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bcampos.shufflesongs.R
import com.example.bcampos.shufflesongs.domain.Song

class SongViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val trackNameText = itemView.findViewById<TextView>(R.id.track_name)

    fun updateSong(song: Song) {
        trackNameText.text = song.trackName
    }
}