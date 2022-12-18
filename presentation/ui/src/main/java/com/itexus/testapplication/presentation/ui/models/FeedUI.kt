package com.itexus.testapplication.presentation.ui.models

data class FeedUI(
    val id: String = "",
    val author: AuthorUI = AuthorUI(),
    val copyright: String = "",
    val country: String = "",
    val icon: String = "",
    val links: List<LinkUI> = listOf(),
    val results: List<ResultUI> = listOf(),
    val title: String = "",
    val updated: String = "",
)