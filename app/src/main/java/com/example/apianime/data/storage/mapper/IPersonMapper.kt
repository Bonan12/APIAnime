package com.example.apianime.data.storage.mapper

import com.example.apianime.data.storage.model.PersonData
import com.example.apianime.domain.model.Person

interface IPersonMapper{
    fun toDomain(personData: PersonData) : Person

    fun toDb(person: Person) : PersonData
}

class PersonMapper : IPersonMapper {
    override fun toDomain(personData: PersonData): Person {
        return Person(
            name = personData.name ?: "",
            role = personData.role ?: "",
            photo = personData.photo ?: ""
        )
    }

    override fun toDb(person: Person): PersonData {
        return PersonData(
            name = person.name,
            role = person.role,
            photo = person.photo,
        )
    }
}