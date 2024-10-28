package com.example.apianime.data.network.repo

import com.example.apianime.data.network.mapper.ITitleDbMapper
import com.example.apianime.data.network.service.TitleRetrofitService
import com.example.apianime.data.storage.dao.ITitleDao
import com.example.apianime.data.storage.mapper.ITitleMapper
import com.example.apianime.domain.model.Title
import com.example.apianime.domain.repo.ITitleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class TitleRepository(
    private val titleDao: ITitleDao,
    private val titleMapper: ITitleMapper,
    private val titleDbMapper: ITitleDbMapper,
    private val service: TitleRetrofitService
) : ITitleRepository {
    override suspend fun getTitles(): Flow<List<Title>> {
        getTitleById(111)
        return flow {
            emit(
                titleDao.getTitles().first().map { title ->
                    titleMapper.invoke(title = title)
                }
            )
        }
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

    private suspend fun getTitleById(id: Int){
        val titleApi = service.getTitleById(id)
        val titleDb = titleDbMapper.invoke(titleApi)
        titleDao.insertTitle(titleDb)
    }

    private suspend fun getRandom(){
        val titleApi = service.getRandomAnime()
        val titleDb = titleDbMapper.invoke(titleApi)
        titleDao.insertTitle(titleDb)
    }
}