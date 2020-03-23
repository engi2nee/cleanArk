package com.c_od_e.mobile_ui.injection.module

import com.c_od_e.data.ProjectsDataRepository
import com.c_od_e.domain.repository.ProjectsRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {

    @Binds
    abstract fun bindDataRepository(dataRepository: ProjectsDataRepository): ProjectsRepository
}