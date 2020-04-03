package com.okanserdaroglu.room.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.okanserdaroglu.room.R
import com.okanserdaroglu.room.data.Note
import com.okanserdaroglu.room.helper.NoteListener
import kotlinx.android.synthetic.main.note_item.view.*

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    private lateinit var noteList: List<Note>
    private lateinit var noteListener: NoteListener


    fun setListener(noteListener: NoteListener) {
        this.noteListener = noteListener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return ViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = noteList[position]
        holder.onBind(note,noteListener)
    }


    override fun getItemCount(): Int {
        return noteList.size
    }

    fun getNoteAtPosition(position: Int): Note {
        return noteList[position]
    }

    fun setNotes(noteList: List<Note>) {
        this.noteList = noteList
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private var textViewTitle: TextView = itemView.textViewTitle
        private var textViewDescription: TextView = itemView.textViewDescription
        private var textViewPriority: TextView = itemView.textViewPriority
        private lateinit var note: Note
        private lateinit var noteListener : NoteListener

        init {
            itemView.setOnClickListener(this)
        }

        fun onBind(note: Note, noteListener: NoteListener) {
            this.note = note
            this.noteListener = noteListener
            textViewTitle.text = note.title
            textViewDescription.text = note.description
            textViewPriority.text = note.priority.toString()
        }

        override fun onClick(v: View?) {
            noteListener.sendNoteInAddNoteFragment(note)
        }

    }

}