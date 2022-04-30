package com.demo.navitask.domain.use_case

import com.demo.navitask.data.PullRepository
import com.demo.navitask.data.model.PullResponse
import com.demo.navitask.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PullRequestUseCase @Inject
constructor(private val repository: PullRepository) {

    operator fun invoke(): Flow<Resource<List<PullResponse>>> = flow {

    }
}