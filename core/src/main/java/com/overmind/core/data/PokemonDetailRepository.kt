package com.overmind.core.data

class PokemonDetailRepository(
    val dataSource: PokemonDetailDataSource
) {

    suspend fun getPokemonDetail(id: Int) = dataSource.getPokemonDetail(id)
}