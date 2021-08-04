package com.overmind.core.data

import com.overmind.core.domain.PokemonDetail

interface PokemonDetailDataSource {

    suspend fun getPokemonDetail(id: Int): RepositoryResponse<PokemonDetail>
}