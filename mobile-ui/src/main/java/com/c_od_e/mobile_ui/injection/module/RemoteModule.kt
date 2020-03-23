package com.c_od_e.mobile_ui.injection.module

import com.c_od_e.data.repository.ProjectsRemote
import com.c_od_e.mobile_ui.BuildConfig
import com.c_od_e.remote.ProjectsRemoteImpl
import com.c_od_e.remote.service.GithubTrendingService
import com.c_od_e.remote.service.GithubTrendingServiceFactory
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class RemoteModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideGithubService(): GithubTrendingService {
            return GithubTrendingServiceFactory.makeGithubTrendingService(BuildConfig.DEBUG)
        }
    }

    @Binds
    abstract fun bindProjectsRemote(projectsRemote: ProjectsRemoteImpl): ProjectsRemote
}