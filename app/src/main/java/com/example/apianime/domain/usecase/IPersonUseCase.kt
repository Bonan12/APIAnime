package com.example.apianime.domain.usecase

import com.example.apianime.domain.model.Person
import com.example.apianime.domain.repo.IPersonRepository
import kotlinx.coroutines.flow.Flow

interface IPersonUseCase {
    suspend fun save(person: Person)

    fun get() : Flow<Person>
}

class PersonUseCase(private val repo: IPersonRepository) : IPersonUseCase {
    override suspend fun save(person: Person) {
        repo.save(person)
    }

    override fun get(): Flow<Person> {
        return repo.get()
    }
}