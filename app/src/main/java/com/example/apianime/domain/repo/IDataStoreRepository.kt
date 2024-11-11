package com.example.apianime.domain.repo

import kotlinx.coroutines.flow.Flow

/**
 * @author Evgenii
 */
interface IDataStoreRepository {
    suspend fun save(isIncreased: Boolean)

    fun get(): Flow<Boolean>
}