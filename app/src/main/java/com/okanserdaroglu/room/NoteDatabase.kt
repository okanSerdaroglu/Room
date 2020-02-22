package com.okanserdaroglu.room

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase


/** @Database creates a database.
 *  entities is an array of tables in database
 *  version is version number of database. When there is a
 *  change in database version number will going to chance
 */

/** if two threads want to access this method synchronized
 * does not give permission it for dont creating multiple instances**/

@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    /** normally you can not call abstract methods directly. Because they don't have body.
     *  However when you create an instance of NoteDatabase Room automatically generates
     *  body of this abstract method.
     */

    companion object {

        private var INSTANCE: NoteDatabase? = null

        fun getInstance(context: Context): NoteDatabase? {
            if (INSTANCE == null) {
                synchronized(NoteDatabase::class) {
                    INSTANCE = databaseBuilder(
                        context.applicationContext,
                        NoteDatabase::class.java,
                        "note_database"
                    ).addCallback(roomCallBack).build()
                }
            }
            return INSTANCE
        }

        private var roomCallBack: Callback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) { // do something after database has been created
                INSTANCE?.let { PopulateDbAsyncTask(it).execute() }
            }

            override fun onOpen(db: SupportSQLiteDatabase) { // do something every time database is open
            }
        }

        class PopulateDbAsyncTask(db: NoteDatabase) : AsyncTask<Void, Void, Void>() {

            var noteDao: NoteDao = db.noteDao()

            override fun doInBackground(vararg p0: Void?): Void? {
                noteDao.insert(Note(1, "Title 1", "Description 1",1))
                noteDao.insert(Note(2, "Title 2", "Description 2",2))
                noteDao.insert(Note(3, "Title 3", "Description 3",3))
                return null

            }

        }

    }

}