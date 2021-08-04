package com.overmind.mywork.framework

import com.overmind.core.data.PokemonDetailDataSource
import com.overmind.mywork.framework.network.PokedexApiClient

class NetworkPokemonDetailDataSource(
    private val apiClient: PokedexApiClient,
) : PokemonDetailDataSource {

    override suspend fun getPokemonDetail(id: Int) = apiClient.getPokemonDetail(id)
}