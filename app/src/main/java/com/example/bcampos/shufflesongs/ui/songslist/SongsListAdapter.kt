package com.example.bcampos.shufflesongs.ui.songslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bcampos.shufflesongs.R
import com.example.bcampos.shufflesongs.domain.Song

class SongsListAdapter(private var songs: MutableList<Song>): RecyclerView.Adapter<SongViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        return SongViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.songs_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return songs.size
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        holder.updateSong(songs[position])
    }

    fun updateList(songs: List<Song>) {
        this.songs.clear()
        this.songs.addAll(songs)
        notifyDataSetChanged()
    }
}