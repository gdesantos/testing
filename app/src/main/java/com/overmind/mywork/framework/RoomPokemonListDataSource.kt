package com.overmind.mywork.framework

import android.content.Context
import com.overmind.core.data.PokemonListDataSource
import com.overmind.core.data.Success
import com.overmind.core.domain.PokemonListItem
import com.overmind.mywork.framework.database.PokemonDatabase

class RoomPokemonListDataSource(private val context: Context) : PokemonListDataSource {

    override suspend fun getList(pageIndex: Int, pageSize: Int) = Success(
        PokemonDatabase.getInstance(context).pokemonListItemDao().getList(pageIndex, pageSize).map {
            PokemonListItem(it.name, it.url)
        }
    )
}