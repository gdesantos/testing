package com.overmind.mywork.framework.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.overmind.mywork.framework.database.entities.PokemonListItemEntity

@Dao
interface PokemonListItemDao {
    @Query("SELECT * FROM PokemonListItemEntity LIMIT :pageSize OFFSET :pageIndex*:pageSize")
    suspend fun getList(pageIndex: Int, pageSize: Int): List<PokemonListItemEntity>

    @Insert
    suspend fun insertAll(items: List<PokemonListItemEntity>)
}