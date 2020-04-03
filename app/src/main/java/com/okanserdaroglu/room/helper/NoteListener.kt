package com.okanserdaroglu.room.helper

import com.okanserdaroglu.room.data.Note

interface NoteListener {

    fun saveNote(note: Note)

    fun updateNote (note : Note)

    fun sendNoteInAddNoteFragment (note:Note)

}