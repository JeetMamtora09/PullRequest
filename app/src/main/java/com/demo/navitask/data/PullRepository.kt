package com.demo.navitask.data

import com.demo.navitask.data.model.PullResponse

interface PullRepository {

    suspend fun getAllPullRequests(): List<PullResponse>

}