package com.example.marvelapi.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "characters")
data class CharactersRepo(
    @PrimaryKey @field:SerializedName("id") val id: Long,
    @field:SerializedName("name") val name: String
)

