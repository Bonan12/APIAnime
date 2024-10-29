package com.example.apianime.data.network.repo

import com.example.apianime.data.network.mapper.ITitleDbMapper
import com.example.apianime.data.network.service.TitleRetrofitService
import com.example.apianime.data.storage.dao.ITitleDao
import com.example.apianime.data.storage.mapper.ITitleMapper
import com.example.apianime.domain.model.Title
import com.example.apianime.domain.repo.ITitleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class TitleRepository(
    private val titleDao: ITitleDao,
    private val titleMapper: ITitleMapper,
    private val titleDbMapper: ITitleDbMapper,
    private val service: TitleRetrofitService
) : ITitleRepository {
    override suspend fun getTitles(): Flow<List<Title>> {
        var result = flow { emit(emptyList<Title>()) }
        withContext(Dispatchers.IO) {
            if (getTitleById(111)) {
                result = flow {
                    emit(
                        titleDao.getTitles().first().map { title ->
                            titleMapper.invoke(title = title)
                        }
                    )
                }
            }
        }
        return result
    }

//    private suspend fun getTitlesByQuery(){
//        val titleApi = service.getTitlesByQuery(limit = 1)
//        with(titleApi.titles
//            .map {
//                titleDbMapper
//                    .invoke(it!!)
//            }
//        ) {
//            forEach {
//                titleDao.insertTitle(it)
//            }
//        }
//    }

    private suspend fun getTitleById(id: Int): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val titleApi = service.getTitleById(id)
                val titleDb = titleDbMapper.invoke(titleApi)
                titleDao.insertTitle(titleDb)
                true
            } catch (e: Exception) {
                false
            }
        }
    }

    private suspend fun getRandom() {
        withContext(Dispatchers.IO) {
            val titleApi = service.getRandomAnime()
            val titleDb = titleDbMapper.invoke(titleApi)
            titleDao.insertTitle(titleDb)
        }
    }
}