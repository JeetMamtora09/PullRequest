package com.demo.navitask.data.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("login")
    val name: String?=null,
    @SerializedName("avatar_url")
    val avatarUrl:String?=null
)