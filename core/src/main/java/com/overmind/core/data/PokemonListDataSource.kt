package com.overmind.core.data

import com.overmind.core.domain.PokemonListItem

interface PokemonListDataSource {

    suspend fun getList(pageIndex: Int, pageSize: Int): RepositoryResponse<List<PokemonListItem>>
}