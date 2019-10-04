package com.example.bcampos.shufflesongs.ui.songslist

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bcampos.shufflesongs.R
import com.example.bcampos.shufflesongs.domain.Song
import com.example.bcampos.shufflesongs.ui.songslist.utils.ImageLoader

class SongViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val trackNameText = itemView.findViewById<TextView>(R.id.track_name)
    private val trackImage = itemView.findViewById<ImageView>(R.id.track_image)
    private val trackArtist = itemView.findViewById<TextView>(R.id.track_artist)
    private val trackGender = itemView.findViewById<TextView>(R.id.track_gender)

    fun updateSong(song: Song, imageLoader: ImageLoader) {
        trackNameText.text = song.trackName
        imageLoader.load(trackImage, song.artworkUrl)
        trackArtist.text = song.artistName
        trackGender.text = String.format("(%s)", song.primaryGenreName)
    }
}