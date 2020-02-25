package com.okanserdaroglu.room.repository

import androidx.lifecycle.LiveData
import androidx.room.*
import com.okanserdaroglu.room.data.Note

/** Dao is a database object
 *  methods created in this interface
 *  @Insert,  @Update ,@Delete annotations for basic operations
 *  @Query for specific operations. sql queries should be true in this annotation
 *  also room can return liveData
 */

@Dao
interface NoteDao {

    @Insert
    fun insert (note: Note)

    @Update
    fun update (note: Note)

    @Delete
    fun delete (note: Note)

    @Query("DELETE FROM note_table")
    fun deleteAllNotes ()

    @Query("SELECT * FROM note_table ORDER BY priority DESC") /** returns live Note list from SELECT query */
    fun getAllNotes () : LiveData<List<Note>>

}