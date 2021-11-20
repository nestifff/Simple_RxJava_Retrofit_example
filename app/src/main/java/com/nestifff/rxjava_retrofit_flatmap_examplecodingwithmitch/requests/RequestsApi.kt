package com.nestifff.rxjava_retrofit_flatmap_examplecodingwithmitch.requests

import com.nestifff.rxjava_retrofit_flatmap_examplecodingwithmitch.models.Comment
import com.nestifff.rxjava_retrofit_flatmap_examplecodingwithmitch.models.Post
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface RequestsApi {

    @GET("posts")
    fun getPosts(): Observable<List<Post>>

    @GET("post/{id}/comments")
    fun getComments(@Path("id") id: Int): Observable<List<Comment>>
}