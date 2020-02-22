package com.okanserdaroglu.room.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/** entity is a Room annotation means a table in Room database
 *  entity means create table
 *  room automatically generates columns of class variables
 *  you can ignore classes using @Ignore annotation
 *  change column name using @ColumnInfo annotation
 * */


@Entity(tableName = "note_table")
data class Note(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")          var id: Int,
    @ColumnInfo(name = "title")       var title: String,
    @ColumnInfo(name = "description") var description : String,
    @ColumnInfo(name = "priority")    var priority : Int)



