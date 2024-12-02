package com.example.apianime.data.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.apianime.data.storage.model.TitleDb

@Dao
interface ITitleDao {
    @Query("SELECT * FROM titles")
    suspend fun getTitles(): List<TitleDb>

    @Insert
    suspend fun insertTitle(titleDb: TitleDb)
}