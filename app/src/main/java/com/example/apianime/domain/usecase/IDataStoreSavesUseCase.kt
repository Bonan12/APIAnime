package com.example.apianime.domain.usecase

import com.example.apianime.domain.repo.IDataStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

/**
 * @author Evgenii
 */

interface IDataStoreSavesUseCase{
    suspend fun save(isIncreased: Boolean)

    fun get(): Flow<Boolean>
}

class DataStoreSavesUseCase(
    private val repository: IDataStoreRepository
):IDataStoreSavesUseCase{
    override suspend fun save(isIncreased: Boolean) {
        repository.save(isIncreased)
    }

    override fun get(): Flow<Boolean> {
        return repository.get()
    }

}