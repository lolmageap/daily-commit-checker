package com.example.entity

import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.Id

data class Article(
    @BsonId
    val id: Id<Article>? = null,

    val title: String,
    val body: String
) {
    fun toDto(): CreateArticle =
        CreateArticle(
            id = this.id.toString(),
            title = this.title,
            body = this.body
        )
}