package com.overmind.core.interactors

import com.overmind.core.data.PokemonDetailRepository

class GetPokemonDetailInteractor(
    val repository: PokemonDetailRepository
){

    suspend fun getPokemonDetail(id: Int) = repository.getPokemonDetail(id)
}