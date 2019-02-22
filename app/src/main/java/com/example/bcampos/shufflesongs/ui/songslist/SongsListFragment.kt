package com.example.bcampos.shufflesongs.ui.songslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.bcampos.shufflesongs.R
import kotlinx.android.synthetic.main.songs_list_fragment.*

class SongsListFragment : Fragment() {

    private var message: TextView? = null

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
    }
    var name = ""

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SongsListViewModel::class.java)
        viewModel.start()
        viewModel.name.observe(this, Observer<String> {
             message?.text = it
        } )
        // TODO: Use the ViewModel
    }

}
