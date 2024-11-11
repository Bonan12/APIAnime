package com.example.apianime.data.storage.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "titles")
data class TitleDb(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo("name") val name: String?,
    @ColumnInfo("type") val type: String?,
    @ColumnInfo("genres") val genres: List<String>?,
    @ColumnInfo("rating") val rating: String?,
    @ColumnInfo("poster") val poster: String?,
    @ColumnInfo("favourite") val isFavourite: Boolean = false
)