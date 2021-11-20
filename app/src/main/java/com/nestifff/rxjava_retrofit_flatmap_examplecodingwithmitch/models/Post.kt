package com.nestifff.rxjava_retrofit_flatmap_examplecodingwithmitch.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Post {

    @SerializedName("userId")
    @Expose
    val userId: Int? = null

    @SerializedName("id")
    @Expose
    val id: Int? = null

    @SerializedName("title")
    @Expose
    val title: String? = null

    @SerializedName("body")
    @Expose
    val body: String? = null

    var comments: List<Comment> = mutableListOf()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Post

        if (userId != other.userId) return false
        if (id != other.id) return false
        if (title != other.title) return false
        if (body != other.body) return false

        return true
    }

    override fun hashCode(): Int {
        var result = userId ?: 0
        result = 31 * result + (id ?: 0)
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + (body?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "Post(userId=$userId, id=$id, title=$title, body=$body, comments=${comments.size})"
    }


}