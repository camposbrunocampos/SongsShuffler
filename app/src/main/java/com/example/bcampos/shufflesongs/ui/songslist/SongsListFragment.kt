package com.example.bcampos.shufflesongs.ui.songslist

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bcampos.shufflesongs.DependencyManager
import com.example.bcampos.shufflesongs.R
import com.example.bcampos.shufflesongs.domain.Song
import com.example.bcampos.shufflesongs.data.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class SongsListFragment : Fragment() {

    private lateinit var dependencyManager: DependencyManager

    private var songsRecyclerView: RecyclerView? = null
    private var progressBar: ProgressBar? = null
    private var errorMessage: TextView? = null
    private var songsListAdapter: SongsListAdapter? = null

    private lateinit var viewModel: SongsListViewModel

    companion object {
        fun newInstance() = SongsListFragment()

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dependencyManager = context.applicationContext as DependencyManager
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.songs_list_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        songsRecyclerView = view.findViewById(R.id.songs_recycler_view)
        errorMessage = view.findViewById(R.id.error_message)
        progressBar = view.findViewById(R.id.progress_bar)

        setupSongsRecyclerView()
    }

    private fun setupSongsRecyclerView() {
        songsRecyclerView?.layoutManager = LinearLayoutManager(context)
        songsListAdapter = SongsListAdapter(mutableListOf(), dependencyManager.getImageLoader())
        songsRecyclerView?.adapter = songsListAdapter
        songsRecyclerView?.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, dependencyManager.getSongViewModelFactory()).get(SongsListViewModel::class.java)
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_songs_list, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_shuffle) {
            viewModel.shuffleSongs()
        }
        return super.onOptionsItemSelected(item)
    }

}
