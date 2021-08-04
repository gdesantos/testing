package com.overmind.mywork.framework.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.overmind.mywork.framework.database.daos.PokemonListItemDao
import com.overmind.mywork.framework.database.entities.PokemonListItemEntity

@Database(entities = [PokemonListItemEntity::class], version = 1)
abstract class PokemonDatabase : RoomDatabase() {

    companion object {

        private var instance: PokemonDatabase? = null

        private fun create(context: Context): PokemonDatabase =
            Room.databaseBuilder(context, PokemonDatabase::class.java, "pokemon.db")
                .fallbackToDestructiveMigration()
                .build()

        fun getInstance(context: Context): PokemonDatabase =
            instance ?: create(context).also {
                instance = it
            }
    }

    abstract fun pokemonListItemDao(): PokemonListItemDao
}