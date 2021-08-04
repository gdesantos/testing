package com.overmind.core.interactors

import com.overmind.core.data.PokemonListRepository

class GetPokemonListInteractor(private val pokemonListRepository: PokemonListRepository) {

    suspend operator fun invoke(pageIndex: Int, pageSize: Int) = pokemonListRepository.getList(pageIndex, pageSize)
}