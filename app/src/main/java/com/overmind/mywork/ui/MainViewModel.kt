package com.overmind.mywork.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.overmind.core.data.RepositoryResponse
import com.overmind.core.data.Success
import com.overmind.core.domain.PokemonListItem
import com.overmind.core.interactors.GetPokemonListInteractor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val getPokemonListInteractor: GetPokemonListInteractor
) : ViewModel() {

    enum class State {
        Idle, Loading
    }

    private val _state = MutableStateFlow(State.Idle)
    val state: StateFlow<State> = _state

    private val _pokemonList = MutableStateFlow<RepositoryResponse<List<PokemonListItem>>>(Success(emptyList()))
    val pokemonList: StateFlow<RepositoryResponse<List<PokemonListItem>>> = _pokemonList

    init {
        viewModelScope.launch {
            _state.value = State.Loading
            getPokemonListInteractor(0, 50).let {
                _pokemonList.emit(it)
            }
            _state.value = State.Idle
        }
    }
}