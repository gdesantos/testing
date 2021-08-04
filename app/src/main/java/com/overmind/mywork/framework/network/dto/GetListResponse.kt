package com.overmind.mywork.framework.network.dto

data class GetListResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: Array<Result>
) {
    data class Result(
        val name: String,
        val url: String
    )
}