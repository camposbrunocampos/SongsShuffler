package com.example.bcampos.shufflesongs

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bcampos.shufflesongs.ui.songslist.SongsListFragment

class SongsListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.songs_list_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SongsListFragment.newInstance())
                .commitNow()
        }
    }

}
