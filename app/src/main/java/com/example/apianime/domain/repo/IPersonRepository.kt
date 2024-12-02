package com.example.apianime.domain.repo

import com.example.apianime.domain.model.Person
import kotlinx.coroutines.flow.Flow

interface IPersonRepository {
    suspend fun save(person: Person)

    fun get() : Flow<Person>
}