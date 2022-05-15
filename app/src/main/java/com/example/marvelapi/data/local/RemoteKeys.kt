package com.example.marvelapi.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKeys(
    @PrimaryKey
    val characterId: Long,
    val prevKey: Int?,
    val nextKey: Int?
)