package com.overmind.mywork.framework.network

import com.fasterxml.jackson.databind.DeserializationFeature
import com.overmind.core.data.Failure
import com.overmind.core.data.RepositoryResponse
import com.overmind.core.data.Success
import com.overmind.core.domain.PokemonDetail
import com.overmind.core.domain.PokemonListItem
import com.overmind.mywork.framework.network.dto.GetListResponse
import com.overmind.mywork.framework.network.dto.GetPokemonDetailResponse
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*

class PokedexApiClient(
    val url: String
) {

    private val httpClient = HttpClient(OkHttp) {
        install(JsonFeature) {
            serializer = JacksonSerializer {
                enable(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES)
                disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            }
        }
    }

    suspend fun getList(pageIndex: Int, pageSize: Int): RepositoryResponse<List<PokemonListItem>> = handler {
        httpClient.get<GetListResponse>("$url/pokemon?limit=$pageSize&offset=${pageIndex*pageSize}").let { response ->
            response.results.map {
                PokemonListItem(it.name, it.url)
            }
        }
    }

    suspend fun getPokemonDetail(id: Int): RepositoryResponse<PokemonDetail> = handler {
        httpClient.get<GetPokemonDetailResponse>("$url/pokemon/$id").let {
            PokemonDetail(it.id, it.name, it.height)
        }
    }

    private suspend fun <T> handler(request: suspend () -> T): RepositoryResponse<T> {
        return try {
            Success(request())
        } catch(e: Throwable) {
            Failure(0)
        }
    }
}