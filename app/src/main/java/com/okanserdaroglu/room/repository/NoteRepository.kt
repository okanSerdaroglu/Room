package com.okanserdaroglu.room.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.okanserdaroglu.room.data.Note

class NoteRepository(application: Application) {

    private var allNotes: LiveData<List<Note>>
    private var noteDao: NoteDao

    init {
        val database = NoteDatabase.getInstance(application)
        noteDao = database!!.noteDao()
        allNotes = noteDao.getAllNotes()
    }

    fun insert(note: Note) {
        NoteAsyncTask(
            noteDao,
            Operations.INSERT
        ).execute(note)
    }

    fun update(note: Note) {
        NoteAsyncTask(
            noteDao,
            Operations.UPDATE
        ).execute(note)
    }

    fun delete(note: Note) {
        NoteAsyncTask(
            noteDao,
            Operations.DELETE
        ).execute(note)
    }

    fun deleteAllNotes() {
        NoteAsyncTask(
            noteDao,
            Operations.DELETE_ALL
        ).execute()
    }

    fun getAllNotes(): LiveData<List<Note>> {
        return allNotes
    }


    /** all database operations must be in background thread with room */
    class NoteAsyncTask(private var noteDao: NoteDao, private var operation: Operations) :
        AsyncTask<Note, Void, Void>() {

        override fun doInBackground(vararg note: Note?): Void? {
            /** vararg is an array */
            when (operation) {
                Operations.INSERT -> {
                    note[0]?.let { noteDao.insert(it) }
                }
                Operations.DELETE -> {
                    note[0]?.let { noteDao.delete(it) }
                }
                Operations.UPDATE -> {
                    note[0]?.let { noteDao.update(it) }
                }
                Operations.DELETE_ALL -> {
                    noteDao.deleteAllNotes()
                }
                Operations.GET_ALL -> {
                    noteDao.getAllNotes()
                }

            }
            return null
        }
    }


    enum class Operations {
        INSERT, UPDATE, DELETE, DELETE_ALL, GET_ALL
    }

}