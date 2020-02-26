package com.okanserdaroglu.room.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.okanserdaroglu.room.R
import com.okanserdaroglu.room.adapter.NoteAdapter
import com.okanserdaroglu.room.viewModel.NoteViewModel

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val recyclerViewNotes = activity?.findViewById<RecyclerView>(R.id.recyclerViewNotes)
        recyclerViewNotes?.layoutManager = LinearLayoutManager(activity)
        recyclerViewNotes?.setHasFixedSize(true)
        val adapter = NoteAdapter()
        recyclerViewNotes?.adapter = adapter

        val noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        noteViewModel.getAllNotes().observe(viewLifecycleOwner, Observer {
            adapter.setNotes(it)
        })

    }

}