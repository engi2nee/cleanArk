package com.c_od_e.mobile_ui.injection.module

import android.app.Application
import com.c_od_e.cache.ProjectsCacheImpl
import com.c_od_e.cache.db.ProjectsDatabase
import com.c_od_e.data.repository.ProjectsCache
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class CacheModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun providesDataBase(application: Application): ProjectsDatabase {
            return ProjectsDatabase.getInstance(application)
        }
    }

    @Binds
    abstract fun bindProjectsCache(projectsCache: ProjectsCacheImpl): ProjectsCache
}