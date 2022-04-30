package com.demo.navitask.network

class BaseUrlImpl : BaseUrl {
    override fun getBaseUrl(): String =
        "https://api.github.com/"
}