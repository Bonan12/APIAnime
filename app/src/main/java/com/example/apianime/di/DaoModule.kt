package com.example.apianime.di

import android.content.Context
import androidx.room.Room
import com.example.apianime.data.storage.dao.ITitleDao
import com.example.apianime.data.storage.dao.TitleDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * @author Evgenii
 */

const val DbName = "anime.db"
val dbModule = module {
    single<TitleDatabase> { provideRoomDatabase(androidContext()) }
    single<ITitleDao> { provideUserDao(get()) }
}


fun provideRoomDatabase(context: Context): TitleDatabase {
    val database: TitleDatabase?
    database = Room.databaseBuilder(context, TitleDatabase::class.java, DbName)
        .fallbackToDestructiveMigration()
        .build()
    return database
}

private fun provideUserDao(database: TitleDatabase): ITitleDao {
    return database.titleDao()
}
