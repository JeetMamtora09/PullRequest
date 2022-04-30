package com.demo.navitask.di

import com.demo.navitask.network.PullRequestApi
import com.demo.navitask.data.PullRepository
import com.demo.navitask.domain.PullRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideCatFactRepository(
        api: PullRequestApi
    ): PullRepository =
        PullRepositoryImpl(api)

}