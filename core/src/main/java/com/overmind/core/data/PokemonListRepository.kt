package com.overmind.core.data

import com.overmind.core.domain.PokemonListItem

class PokemonListRepository(
    private val dataSource: PokemonListDataSource
) {

    suspend fun getList(pageIndex: Int, pageSize: Int): RepositoryResponse<List<PokemonListItem>> {
        return dataSource.getList(pageIndex, pageSize)
    }
}