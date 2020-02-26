package com.okanserdaroglu.room.ui

import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.okanserdaroglu.room.R

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
        editTextTitle = view.findViewById(R.id.editTextTitle)
        editTextDescription = view.findViewById(R.id.editTextDescription)
        numberPickerPriority = view.findViewById(R.id.numberPickerPriority)

        numberPickerPriority.minValue = 1
        numberPickerPriority.maxValue = 10
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        val menuInflater: MenuInflater = activity!!.menuInflater
        menuInflater.inflate(R.menu.add_note_menu, menu)
    }

    private fun saveNote() {
        val title: String = editTextTitle.text.toString()
        val description: String = editTextDescription.text.toString()
        var priority: Int = numberPickerPriority.value

        if (title.trim().isEmpty()
            || description.trim().isEmpty()
        ) {
            Toast.makeText(activity, "Please insert title and description", Toast.LENGTH_LONG)
                .show()
            return
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

}