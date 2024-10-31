package com.example.apianime.data.storage.dao

import com.example.apianime.data.storage.mock.MockData
import com.example.apianime.data.storage.model.TitleDb
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface ITitleDao {
    suspend fun getTitles(): Flow<List<TitleDb>>

    suspend fun insertTitle(titleDb: TitleDb): Boolean
}

class TitleDao : ITitleDao {
    private val mockData: MockData = MockData()

    override suspend fun getTitles(): Flow<List<TitleDb>> {
        return flow { emit(mockData.titles) }
    }

    override suspend fun insertTitle(titleDb: TitleDb): Boolean {
        val size = mockData.titles.size
        mockData.titles.add(titleDb)
        return size < mockData.titles.size
    }
}