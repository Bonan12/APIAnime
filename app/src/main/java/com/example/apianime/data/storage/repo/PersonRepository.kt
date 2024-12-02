package com.example.apianime.data.storage.repo

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.example.apianime.data.storage.mapper.IPersonMapper
import com.example.apianime.data.storage.model.PersonData
import com.example.apianime.data.storage.util.PersonSerializer
import com.example.apianime.domain.model.Person
import com.example.apianime.domain.repo.IPersonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.qualifier.named
import org.koin.java.KoinJavaComponent.inject

class PersonRepository(
    private val mapper: IPersonMapper,
) : IPersonRepository {
    private val proto : DataStore<PersonData> by inject(DataStore::class.java, named("person"))
    /**
     * сохранить информацию
     */
    override suspend fun save(person: Person) {
        proto.updateData {
            it.copy(
                name = person.name,
                role = person.role,
                photo = person.photo,
            )
        }
    }

    override fun get(): Flow<Person> {
        return proto.data.map {
            mapper.toDomain(it)
        }
    }
}

class DataSourceProvider(private val context: Context) {
    private val Context.profileDataStore: DataStore<PersonData> by dataStore(
        fileName = "person.pb",
        serializer = PersonSerializer
    )

    fun provide() = context.profileDataStore
}