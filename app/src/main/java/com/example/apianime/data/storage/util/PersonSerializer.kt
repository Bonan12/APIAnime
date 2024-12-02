package com.example.apianime.data.storage.util

import androidx.datastore.core.Serializer
import com.example.apianime.data.storage.model.PersonData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

object PersonSerializer : Serializer<PersonData> {
    override val defaultValue: PersonData
        get() = PersonData()

    override suspend fun readFrom(input: InputStream): PersonData {
        return try {
            Json.decodeFromString(
                deserializer = PersonData.serializer(),
                string = input.readBytes().decodeToString()
            )
        } catch (e: SerializationException){
            PersonData()
        }
    }

    override suspend fun writeTo(t: PersonData, output: OutputStream) {
        withContext(Dispatchers.IO) {
            output.write(
                Json.encodeToString(
                    serializer = PersonData.serializer(),
                    value = t
                ).encodeToByteArray()
            )
        }
    }
}