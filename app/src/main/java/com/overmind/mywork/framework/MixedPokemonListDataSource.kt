package com.overmind.mywork.framework

import android.content.Context
import com.overmind.core.data.PokemonListDataSource
import com.overmind.core.data.RepositoryResponse
import com.overmind.core.data.Success
import com.overmind.core.domain.PokemonListItem
import com.overmind.mywork.framework.database.PokemonDatabase
import com.overmind.mywork.framework.database.entities.PokemonListItemEntity

class MixedPokemonListDataSource(
    private val context: Context,
    private val roomDataSource: RoomPokemonListDataSource,
    private val networkDataSource: NetworkPokemonListDataSource
) : PokemonListDataSource {

    override suspend fun getList(pageIndex: Int, pageSize: Int): RepositoryResponse<List<PokemonListItem>> {
        val dbData = roomDataSource.getList(pageIndex, pageSize)

        if(dbData.value.isNotEmpty()) {
            return dbData
        }

        return networkDataSource.getList(pageIndex, pageSize).also { response ->
            if(response is Success) {
                PokemonDatabase.getInstance(context).pokemonListItemDao().insertAll(response.value.map {
                    PokemonListItemEntity(name = it.name, url = it.url)
                })
            }
        }
    }
}