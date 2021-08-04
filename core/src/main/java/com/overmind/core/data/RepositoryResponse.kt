package com.overmind.core.data

sealed interface RepositoryResponse<T>
data class Success<T>(val value: T) : RepositoryResponse<T>
data class Failure<T>(val error: Int) : RepositoryResponse<T>