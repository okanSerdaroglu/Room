package com.okanserdaroglu.room.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.okanserdaroglu.room.R
import com.okanserdaroglu.room.adapter.NoteAdapter
import com.okanserdaroglu.room.data.Note
import com.okanserdaroglu.room.helper.AddNoteListener
import com.okanserdaroglu.room.viewModel.NoteViewModel
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(),View.OnClickListener,AddNoteListener {

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
        buttonAddNote.setOnClickListener(this)
        recyclerViewNotes?.setHasFixedSize(true)
        val adapter = NoteAdapter()
        recyclerViewNotes?.adapter = adapter

        val noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        noteViewModel.getAllNotes().observe(viewLifecycleOwner, Observer {
            adapter.setNotes(it)
        })

    }

    override fun onClick(p0: View?) {
        val action = MainFragmentDirections.actionMainFragmentToAddNoteFragment()
        AddNoteFragment.setListener(this)
        NavHostFragment.findNavController(this).navigate(action)
    }

    override fun saveNote(note: Note) {
        val noteViewModel : NoteViewModel? = activity?.application?.let { NoteViewModel(it) }
        noteViewModel?.insert(note)
    }

}