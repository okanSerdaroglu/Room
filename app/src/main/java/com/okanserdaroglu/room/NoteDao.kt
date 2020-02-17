package com.okanserdaroglu.room

import androidx.room.*

/** Dao is a database object
 *  methods created in this interface
 *  @Insert,  @Update ,@Delete annotations for basic operations
 *  @Query for specific operations
 */

@Dao
interface NoteDao {

    @Insert
    fun insert (note:Note)

    @Update
    fun update (note: Note)

    @Delete
    fun delete (note: Note)

    @Query("DELETE FROM note_table")
    fun deleteAllNotes ()

    @Query("SELECT * FROM note_table ORDER BY priority DESC")
    fun getAllNotes () : List<Note>

}