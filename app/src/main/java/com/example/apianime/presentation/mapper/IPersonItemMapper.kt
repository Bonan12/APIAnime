package com.example.apianime.presentation.mapper

import android.net.Uri
import com.example.apianime.domain.model.Person
import com.example.apianime.presentation.model.PersonItem

interface IPersonItemMapper {
    fun toUi(person: Person) : PersonItem

    fun toDomain(personItem: PersonItem) : Person
}

class PersonItemMapper : IPersonItemMapper{
    override fun toUi(person: Person): PersonItem {
        return PersonItem(
            name = person.name.ifEmpty { "Нет имени" },
            role = person.role.ifEmpty { "Нет роли" },
            photoUri = Uri.parse(person.photo)
        )
    }

    override fun toDomain(personItem: PersonItem): Person {
        return Person(
            name = if (personItem.name == "Нет имени") "" else personItem.name,
            role = if (personItem.role == "Нет роли") "" else personItem.role,
            photo = personItem.photoUri.toString()
        )
    }

}