package ca.on.listech.borutoapp.data.local

import androidx.room.TypeConverter

class DatabaseConverter {

    private val separator = ","

    @TypeConverter
    fun listToString(list: List<String>): String {
        return list.joinToString(separator)
    }

    @TypeConverter
    fun stringToList(str: String): List<String> {
        return str.split(separator)
    }
}