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
import com.example.bcampos.shufflesongs.R
import com.example.bcampos.shufflesongs.State

class SongsListFragment : Fragment() {

    private var message: TextView? = null
    private var progressBar: ProgressBar? = null
    private var errorMessage: TextView? = null

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
        message = view.findViewById(R.id.message)
        errorMessage = view.findViewById(R.id.error_message)
        progressBar = view.findViewById(R.id.progress_bar)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SongsListViewModel::class.java)
        viewModel.start()
        viewModel.songsState.observe(this, Observer<State<String>> {
            when(it.name) {
                State.Name.IDLE -> {

                }
                State.Name.LOADING -> {
                    message!!.visibility = View.GONE
                    progressBar!!.visibility = View.VISIBLE
                    errorMessage!!.visibility = View.GONE
                }
                State.Name.LOADED -> {
                    message!!.visibility = View.VISIBLE
                    progressBar!!.visibility = View.GONE
                    errorMessage!!.visibility = View.GONE
                }

                State.Name.ERROR -> {
                    message!!.visibility = View.GONE
                    progressBar!!.visibility = View.GONE
                    errorMessage!!.visibility = View.VISIBLE
                }
            }
        })
    }

}
