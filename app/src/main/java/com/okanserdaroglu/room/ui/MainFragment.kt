package com.okanserdaroglu.room.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.okanserdaroglu.room.R
import com.okanserdaroglu.room.adapter.NoteAdapter
import com.okanserdaroglu.room.data.Note
import com.okanserdaroglu.room.helper.AddNoteListener
import com.okanserdaroglu.room.viewModel.NoteViewModel
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment(), View.OnClickListener,
    AddNoteListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        val menuInflater: MenuInflater = activity!!.menuInflater
        menuInflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.delete_all_notes) {
            //noteViewModel.deleteAllNotes()
            true
        } else {
            super.onOptionsItemSelected(item)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerViewNotes = activity?.findViewById<RecyclerView>(R.id.recyclerViewNotes)
        recyclerViewNotes?.layoutManager = LinearLayoutManager(activity)
        buttonAddNote.setOnClickListener(this)
        recyclerViewNotes?.setHasFixedSize(true)
        val adapter = NoteAdapter()
        recyclerViewNotes?.adapter = adapter

        var noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        noteViewModel.getAllNotes().observe(viewLifecycleOwner, Observer {
            adapter.setNotes(it)
        })

        ItemTouchHelper(
            object :
                ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    noteViewModel.delete(adapter.getNoteAtPosition(viewHolder.adapterPosition))
                    Toast.makeText(activity, "note deleted", Toast.LENGTH_LONG).show()
                }
            }).attachToRecyclerView(recyclerViewNotes)


    }

    override fun onClick(p0: View?) {
        val action = MainFragmentDirections.actionMainFragmentToAddNoteFragment()
        AddNoteFragment.setListener(this)
        NavHostFragment.findNavController(this).navigate(action)
    }

    override fun saveNote(note: Note) {
        val noteViewModel: NoteViewModel? = activity?.application?.let { NoteViewModel(it) }
        noteViewModel?.insert(note)
    }


}