package com.okanserdaroglu.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase

/** @Database creates a database.
 *  entities is an array of tables in database
 *  version is version number of database. When there is a
 *  change in database version number will going to chance
 */

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {

        private var INSTANCE: NoteDatabase? = null

        fun getAppDataBase(context: Context): NoteDatabase? {
            if (INSTANCE == null) {
                synchronized(NoteDatabase::class) {
                    INSTANCE = databaseBuilder(
                        context.applicationContext,
                        NoteDatabase::class.java,
                        "note_database"
                    ).build()
                }
            }
            return INSTANCE
        }

    }

}