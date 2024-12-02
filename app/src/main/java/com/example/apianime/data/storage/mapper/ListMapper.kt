package com.example.apianime.data.storage.mapper

import androidx.room.TypeConverter

/**
 * @author Evgenii
 */
class ListMapper {

    @TypeConverter
    fun convertListStringToString(list: List<String>?) : String? {
        return list?.joinToString(separator = ", ") { it }
    }

    @TypeConverter
    fun convertStringToListString(string: String?): List<String>? {
        return string?.split(", ")
    }
}