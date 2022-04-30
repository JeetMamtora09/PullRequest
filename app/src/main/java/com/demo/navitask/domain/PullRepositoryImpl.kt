package com.demo.navitask.domain

import android.util.Log
import com.demo.navitask.network.PullRequestApi
import com.demo.navitask.data.PullRepository
import com.demo.navitask.data.model.PullResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PullRepositoryImpl @Inject constructor(
    private val api: PullRequestApi
) : PullRepository {

    override suspend fun getAllPullRequests(): List<PullResponse> = withContext(Dispatchers.IO) {
        api.getAllPullRequest()
    }


}