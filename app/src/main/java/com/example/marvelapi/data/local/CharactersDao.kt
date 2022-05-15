package com.example.marvelapi.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.marvelapi.models.Character

@Dao
interface CharactersDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<CharactersRepo>)

    @Query("SELECT * FROM characters ORDER BY name ASC")
    fun charactersByName(): PagingSource<Int, CharactersRepo>

    @Query("DELETE FROM characters")
    suspend fun clearCharacters()
}

