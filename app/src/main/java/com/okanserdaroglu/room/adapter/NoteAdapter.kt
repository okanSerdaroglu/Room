package com.okanserdaroglu.room.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.okanserdaroglu.room.R
import com.okanserdaroglu.room.data.Note
import kotlinx.android.synthetic.main.note_item.view.*

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    lateinit var noteList: List<Note>

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var textViewTitle: TextView = itemView.textViewTitle
        var textViewDescription: TextView = itemView.textViewDescription
        var textViewPriority: TextView = itemView.textViewPriority

    }

    fun setNotes(noteList: List<Note>) {
        this.noteList = noteList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return ViewHolder(itemView)

    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var note = noteList[position]
        holder.textViewTitle.text = note.title
        holder.textViewDescription.text = note.description
        holder.textViewPriority.text = note.priority.toString()
    }
}