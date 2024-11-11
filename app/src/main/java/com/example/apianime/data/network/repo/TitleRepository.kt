package com.example.apianime.data.network.repo

import com.example.apianime.data.network.mapper.ITitleDbMapper
import com.example.apianime.data.network.service.TitleRetrofitService
import com.example.apianime.data.storage.dao.ITitleDao
import com.example.apianime.data.storage.mapper.ITitleMapper
import com.example.apianime.domain.model.Title
import com.example.apianime.domain.repo.ITitleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class TitleRepository(
    private val titleDao: ITitleDao,
    private val titleMapper: ITitleMapper,
    private val titleDbMapper: ITitleDbMapper,
    private val service: TitleRetrofitService
) : ITitleRepository {
    override suspend fun getTitles(): Set<Title> {
        val result = getTitlesFromDatabase() + getTitlesFromResponse()
        return result.distinctBy { it.name }.toSet()
    }

    override suspend fun getTitlesFromResponse(): Set<Title> {
        return withContext(Dispatchers.IO) {
            try {
                val titlesResponse = service.getTitlesByQuery()
                val titlesDb = titlesResponse.data?.filter { !it?.images?.jpg?.image_url.isNullOrEmpty() }
                    ?.map { titleDbMapper.invoke(it) }
                val titles = titlesDb?.map { titleMapper.toDomain(it) }
                titles?.distinctBy { it.name }?.toSet() ?: emptySet()
            } catch (e: Exception) {
                emptySet()
            }
        }
    }

    override suspend fun save(title: Title) {
        titleDao.insertTitle(titleMapper.toDb(title))
    }

    override suspend fun getTitlesFromDatabase(): Set<Title> {
        return titleDao.getTitles().map { titleMapper.toDomain(it) }.distinctBy { it.name }.toSet()
    }
}