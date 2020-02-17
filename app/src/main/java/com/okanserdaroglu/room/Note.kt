package com.okanserdaroglu.room

import androidx.room.Entity
import androidx.room.PrimaryKey

/** entity is a Room annotation means a table in Room database
 *  entity means create table
 *  room automatically generates columns of class variables
 *  you can ignore classes using @Ignore annotation
 *  change column name using @ColumnInfo annotation
 * */

@Entity(tableName = "note_table")
class note(
    var title: String,
    var description: String,
    var priority: Int
) {

    @PrimaryKey(autoGenerate = true)
    var id = Int
}
