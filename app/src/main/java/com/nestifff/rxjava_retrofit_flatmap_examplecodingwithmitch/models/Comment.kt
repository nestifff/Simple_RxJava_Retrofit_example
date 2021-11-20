package com.nestifff.rxjava_retrofit_flatmap_examplecodingwithmitch.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Comment {

    @Expose
    @SerializedName("postId")
    var postId: Int? = null

    @Expose
    @SerializedName("id")
    var id: Int? = null

    @Expose
    @SerializedName("name")
    var name: String? = null

    @Expose
    @SerializedName("email")
    var email: String? = null

    @Expose
    @SerializedName("body")
    var body: String? = null

}