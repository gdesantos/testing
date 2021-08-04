package com.overmind.mywork.di

import com.overmind.mywork.framework.network.PokedexApiClient
import org.koin.dsl.module

val networkModule = module {
    single { PokedexApiClient("https://pokeapi.co/api/v2") }
}