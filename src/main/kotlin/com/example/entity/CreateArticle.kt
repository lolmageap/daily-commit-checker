package com.example.entity

import kotlinx.serialization.Serializable

@Serializable
data class CreateArticle(
    val id: String? = null,
    val title: String,
    val body: String,
) {
    fun toArticle(): Article =
        Article(
            title = this.title,
            body = this.body
        )
}
