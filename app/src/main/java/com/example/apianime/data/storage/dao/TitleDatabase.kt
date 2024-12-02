package com.example.apianime.data.storage.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.apianime.data.storage.mapper.ListMapper
import com.example.apianime.data.storage.model.TitleDb

/**
 * @author Evgenii
 */
@Database(entities = [TitleDb::class], version = 2)
@TypeConverters(ListMapper::class)
abstract class TitleDatabase : RoomDatabase() {
    abstract fun titleDao(): ITitleDao
}