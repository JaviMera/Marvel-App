package com.example.marvelapi.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.marvelapi.models.Character

@Database(
    entities = [CharactersRepo::class, RemoteKeys::class],
    version = 3,
    exportSchema = false
)
abstract class MarvelDatabase : RoomDatabase() {
    abstract fun charactersDao() : CharactersDao
    abstract fun remoteKeysDao() : RemoteKeysDao

    companion object{
        @Volatile
        private var INSTANCE: MarvelDatabase? = null

        fun getInstance(context: Context) : MarvelDatabase =
            INSTANCE ?: synchronized(this){
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                MarvelDatabase::class.java,
                "marvel.db"
            )
                .build()
    }
}