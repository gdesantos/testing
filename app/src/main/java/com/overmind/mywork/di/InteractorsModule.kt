package com.overmind.mywork.di

import com.overmind.core.interactors.GetPokemonDetailInteractor
import com.overmind.core.interactors.GetPokemonListInteractor
import org.koin.dsl.module

val interactorsModule = module {
    single { GetPokemonListInteractor(get()) }
    single { GetPokemonDetailInteractor(get()) }
}