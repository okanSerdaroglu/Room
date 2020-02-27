package com.okanserdaroglu.room.helper

import com.okanserdaroglu.room.data.Note

interface AddNoteListener {

    fun saveNote(note: Note)

}