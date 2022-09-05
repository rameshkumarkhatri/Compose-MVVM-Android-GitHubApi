package com.mobifyall.githubapi.di

import com.mobifyall.githubapi.core.network.GitHubApiService
import com.mobifyall.githubapi.repos.GitHubRepo
import com.mobifyall.githubapi.repos.GitHubRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ReposModule {

    @Singleton
    @Provides
    fun provideRestaurantSearchRepo(service: GitHubApiService): GitHubRepo {
        return GitHubRepoImpl(service)
    }
}