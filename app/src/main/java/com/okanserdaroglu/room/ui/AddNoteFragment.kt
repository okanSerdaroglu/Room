package com.okanserdaroglu.room.ui

import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.okanserdaroglu.room.R
import com.okanserdaroglu.room.data.Note
import com.okanserdaroglu.room.helper.NoteListener

class AddNoteFragment : Fragment() {

    private lateinit var editTextTitle: EditText
    private lateinit var editTextDescription: EditText
    private lateinit var numberPickerPriority: NumberPicker

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_note, container, false)
        initViews(view)
        return view
    }

    companion object {
        private lateinit var noteListener: NoteListener
        private lateinit var operationType: OperationTypes

        fun setListener(noteListener: NoteListener){
            this.noteListener = noteListener
        }

        fun setOperationType (operationType : OperationTypes){
            this.operationType = operationType
        }

    }


    private fun initViews (view:View){
        editTextTitle = view.findViewById(R.id.editTextTitle)
        editTextDescription = view.findViewById(R.id.editTextDescription)
        numberPickerPriority = view.findViewById(R.id.numberPickerPriority)
        numberPickerPriority.minValue = 1
        numberPickerPriority.maxValue = 10
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        val menuInflater: MenuInflater = activity!!.menuInflater
        menuInflater.inflate(R.menu.add_note_menu, menu)
    }

    private fun saveNote() {
        val title: String = editTextTitle.text.toString()
        val description: String = editTextDescription.text.toString()
        val priority: Int = numberPickerPriority.value

        if (title.trim().isEmpty()
            || description.trim().isEmpty()
        ) {
            Toast.makeText(activity, getString(R.string.empty_note_message), Toast.LENGTH_LONG)
                .show()
            return
        } else {
            val note:Note = Note(0,title,description,priority)
            if (operationType == OperationTypes.INSERT){
                noteListener.saveNote(note)
            } else if (operationType == OperationTypes.UPDATE){
                noteListener.updateNote(note)
            }
            activity?.onBackPressed()
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.save_note) {
            saveNote()
            true
        } else {
            super.onOptionsItemSelected(item)
        }

    }

    enum class OperationTypes {
        INSERT, UPDATE
    }

}