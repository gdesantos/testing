package com.overmind.mywork.di

import com.overmind.core.data.PokemonDetailRepository
import com.overmind.core.data.PokemonListRepository
import com.overmind.mywork.framework.NetworkPokemonListDataSource
import com.overmind.mywork.framework.MixedPokemonListDataSource
import com.overmind.mywork.framework.NetworkPokemonDetailDataSource
import com.overmind.mywork.framework.RoomPokemonListDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    single {
        PokemonListRepository(
            MixedPokemonListDataSource(
                androidContext(),
                RoomPokemonListDataSource(androidContext()),
                NetworkPokemonListDataSource(get())
            )
        )
    }

    single {
        PokemonDetailRepository(
            NetworkPokemonDetailDataSource(get())
        )
    }
}