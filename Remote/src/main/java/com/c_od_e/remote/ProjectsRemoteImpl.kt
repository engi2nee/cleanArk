package com.c_od_e.remote

import com.c_od_e.data.model.ProjectEntity
import com.c_od_e.data.repository.ProjectsRemote
import com.c_od_e.remote.mapper.ProjectsResponseModelMapper
import com.c_od_e.remote.service.GithubTrendingService
import io.reactivex.Flowable
import io.reactivex.Observable
import javax.inject.Inject

open class ProjectsRemoteImpl @Inject constructor(
    private val service: GithubTrendingService,
    private val mapper: ProjectsResponseModelMapper)
    : ProjectsRemote {

    override fun getProjects(): Observable<List<ProjectEntity>> {
        return service.searchRepositories("language:kotlin", "stars", "desc")
            .map {
                it.items.map { mapper.mapFromModel(it) }
            }
    }
}