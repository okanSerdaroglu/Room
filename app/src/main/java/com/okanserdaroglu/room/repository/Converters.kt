package com.okanserdaroglu.room.repository

import androidx.lifecycle.LiveData
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.okanserdaroglu.room.data.Note

class Converters {
    companion object {
        @TypeConverter
        @JvmStatic
        fun fromList(value: LiveData<List<Note>>): String {
            return Gson().toJson(value)
        }

        @TypeConverter
        @JvmStatic
        fun toList(value: String): LiveData<List<Note>> {
            val itemType = object : TypeToken<LiveData<List<Note>>>() {}.type
            return Gson().fromJson<LiveData<List<Note>>>(value, itemType)
        }
    }
}