package com.okanserdaroglu.room.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.okanserdaroglu.room.viewModel.NoteViewModel
import com.okanserdaroglu.room.R
import com.okanserdaroglu.room.adapter.NoteAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerViewNotes = findViewById<RecyclerView>(R.id.recyclerViewNotes)
        recyclerViewNotes.layoutManager = LinearLayoutManager(this)
        recyclerViewNotes.setHasFixedSize(true)
        val adapter : NoteAdapter = NoteAdapter()
        recyclerViewNotes.adapter = adapter

        val noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        noteViewModel.getAllNotes().observe(this, Observer {
            adapter.setNotes(it)
        })

    }
}
