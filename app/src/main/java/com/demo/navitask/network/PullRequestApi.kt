package com.demo.navitask.network

import com.demo.navitask.data.model.PullResponse
import retrofit2.http.GET

interface PullRequestApi {

    @GET("repos/prakashshuklahub/Pull-Request-GithubApi-Demo-App/pulls?state=closed")
    suspend fun getAllPullRequest(): List<PullResponse>

}