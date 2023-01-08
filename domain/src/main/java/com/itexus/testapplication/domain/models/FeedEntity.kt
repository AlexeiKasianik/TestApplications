package com.itexus.testapplication.domain.models

data class FeedEntity(
    val id: String = "",
    val author: AuthorEntity = AuthorEntity(),
    val copyright: String = "",
    val country: String = "",
    val icon: String = "",
    val links: List<LinkEntity> = listOf(),
    val results: List<ResultEntity> = listOf(),
    val title: String = "",
    val updated: String = "",
)