package com.overmind.mywork.framework

import com.overmind.core.data.PokemonListDataSource
import com.overmind.mywork.framework.network.PokedexApiClient

class NetworkPokemonListDataSource(
    private val apiClient: PokedexApiClient,
) : PokemonListDataSource {

    override suspend fun getList(pageIndex: Int, pageSize: Int) = apiClient.getList(pageIndex, pageSize)
}