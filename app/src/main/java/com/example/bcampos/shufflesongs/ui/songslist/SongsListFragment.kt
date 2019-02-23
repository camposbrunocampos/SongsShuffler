package com.example.bcampos.shufflesongs.ui.songslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bcampos.shufflesongs.R
import com.example.bcampos.shufflesongs.domain.Song
import com.example.bcampos.shufflesongs.domain.State
import com.example.bcampos.shufflesongs.data.SongsRepository
import okhttp3.OkHttpClient

class SongsListFragment : Fragment() {

    private var songsRecyclerView: RecyclerView? = null
    private var progressBar: ProgressBar? = null
    private var errorMessage: TextView? = null

    private val songsUseCase = SongsRepository(OkHttpClient())
    private val viewModelFactory = SongsViewModelFactory(songsUseCase)

    private var songsListAdapter: SongsListAdapter? = null

    companion object {
        fun newInstance() = SongsListFragment()
    }

    private lateinit var viewModel: SongsListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.songs_list_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        songsRecyclerView = view.findViewById(R.id.songs_recycler_view)
        errorMessage = view.findViewById(R.id.error_message)
        progressBar = view.findViewById(R.id.progress_bar)

        songsRecyclerView?.layoutManager = LinearLayoutManager(context)
        songsListAdapter = SongsListAdapter(mutableListOf())
        songsRecyclerView?.adapter = songsListAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SongsListViewModel::class.java)
        viewModel.loadSongs()
        viewModel.songsState.observe(this, Observer<State<List<Song>>> {
            when(it.name) {
                State.Name.IDLE -> {

                }
                State.Name.LOADING -> {
                    songsRecyclerView!!.visibility = View.GONE
                    progressBar!!.visibility = View.VISIBLE
                    errorMessage!!.visibility = View.GONE
                }
                State.Name.LOADED -> {
                    songsRecyclerView!!.visibility = View.VISIBLE
                    progressBar!!.visibility = View.GONE
                    errorMessage!!.visibility = View.GONE
                    songsListAdapter?.updateList(it.value!!)

                }

                State.Name.ERROR -> {
                    songsRecyclerView!!.visibility = View.GONE
                    progressBar!!.visibility = View.GONE
                    errorMessage!!.visibility = View.VISIBLE
                }
            }
        })
    }

}
