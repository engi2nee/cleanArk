package com.c_od_e.remote.test

import com.c_od_e.data.model.ProjectEntity
import com.c_od_e.remote.ProjectsRemoteImpl
import com.c_od_e.remote.mapper.ProjectsResponseModelMapper
import com.c_od_e.remote.model.ProjectModel
import com.c_od_e.remote.model.ProjectsResponseModel
import com.c_od_e.remote.service.GithubTrendingService
import com.c_od_e.remote.test.factory.ProjectDataFactory
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Flowable
import io.reactivex.Observable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ProjectsRemoteImplTest {

    private val mapper = mock<ProjectsResponseModelMapper>()
    private val service = mock<GithubTrendingService>()
    private val remote = ProjectsRemoteImpl(service, mapper)

    @Test
    fun getProjectsCompletes() {
        stubGithubTrendingServiceSearchRepositories(Flowable.just(
            ProjectDataFactory.makeProjectsResponse()))
        stubProjectsResponseModelMapperMapFromModel(any(),
            ProjectDataFactory.makeProjectEntity())

        val testObserver = remote.getProjects().test()
        testObserver.assertComplete()
    }

    @Test
    fun getProjectsCallsServer() {
        stubGithubTrendingServiceSearchRepositories(Flowable.just(
            ProjectDataFactory.makeProjectsResponse()))
        stubProjectsResponseModelMapperMapFromModel(any(),
            ProjectDataFactory.makeProjectEntity())

        remote.getProjects().test()
        verify(service).searchRepositories(any(), any(), any())
    }

    @Test
    fun getProjectsReturnsData() {
        val response = ProjectDataFactory.makeProjectsResponse()
        stubGithubTrendingServiceSearchRepositories(Flowable.just(response))
        val entities = mutableListOf<ProjectEntity>()
        response.items.forEach {
            val entity = ProjectDataFactory.makeProjectEntity()
            entities.add(entity)
            stubProjectsResponseModelMapperMapFromModel(it, entity)
        }
        val testObserver = remote.getProjects().test()
        testObserver.assertValue(entities)
    }

    @Test
    fun getProjectsCallsServerWithCorrectParameters() {
        stubGithubTrendingServiceSearchRepositories(Flowable.just(
            ProjectDataFactory.makeProjectsResponse()))
        stubProjectsResponseModelMapperMapFromModel(any(),
            ProjectDataFactory.makeProjectEntity())

        remote.getProjects().test()
        verify(service).searchRepositories("language:kotlin", "stars", "desc")
    }

    private fun stubGithubTrendingServiceSearchRepositories(observable:
                                                            Flowable<ProjectsResponseModel>) {
        whenever(service.searchRepositories(any(), any(), any()))
            .thenReturn(observable)
    }

    private fun stubProjectsResponseModelMapperMapFromModel(model: ProjectModel,
                                                            entity: ProjectEntity) {
        whenever(mapper.mapFromModel(model))
            .thenReturn(entity)
    }

}