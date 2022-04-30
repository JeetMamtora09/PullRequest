package com.demo.navitask.data.model

import com.google.gson.annotations.SerializedName

data class PullResponse(
    @SerializedName("title")
    val title:String?,
    @SerializedName("created_at")
    val createdDate:String?,
    @SerializedName("closed_at")
    val closedDate:String?,
    @SerializedName("user")
    val user: User?=null
)