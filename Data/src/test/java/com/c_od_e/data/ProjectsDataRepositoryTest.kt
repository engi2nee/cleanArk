package com.c_od_e.data

import com.c_od_e.data.mapper.ProjectMapper
import com.c_od_e.data.model.ProjectEntity
import com.c_od_e.data.repository.ProjectsCache
import com.c_od_e.data.repository.ProjectsDataStore
import com.c_od_e.data.store.ProjectsDataStoreFactory
import com.c_od_e.data.test.factory.DataFactory
import com.c_od_e.data.test.factory.ProjectFactory
import com.c_od_e.domain.model.Project
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

class ProjectsDataRepositoryTest {

    private val mapper = mock<ProjectMapper>()
    private val factory = mock<ProjectsDataStoreFactory>()
    private val store = mock<ProjectsDataStore>()
    private val cache = mock<ProjectsCache>()
    private val repository = ProjectsDataRepository(mapper, cache, factory)

    @Before
    fun setup() {
        stubFactoryGetDataStore()
        stubFactoryGetCacheDataStore()
        stubIsCacheExpired(Single.just(false))
        stubAreProjectsCached(Single.just(false))
        stubSaveProjects(Completable.complete())
    }

    @Test
    fun getProjectsCompletes() {
        stubGetProjects(Observable.just(listOf(ProjectFactory.makeProjectEntity())))
        stubMapper(ProjectFactory.makeProject(), any())

        val testObserver = repository.getProjects().test()
        testObserver.assertComplete()
    }

    @Test
    fun getProjectsReturnsData() {
        val projectEntity = ProjectFactory.makeProjectEntity()
        val project = ProjectFactory.makeProject()
        stubGetProjects(Observable.just(listOf(projectEntity)))
        stubMapper(project, projectEntity)

        val testObserver = repository.getProjects().test()
        testObserver.assertValue(listOf(project))
    }

    @Test
    fun getBookmarkedProjectsCompletes() {
        stubGetBookmarkedProjects(Observable.just(listOf(ProjectFactory.makeProjectEntity())))
        stubMapper(ProjectFactory.makeProject(), any())

        val testObserver = repository.getBookmarkedProjects().test()
        testObserver.assertComplete()
    }

    @Test
    fun getBookmarkedProjectsReturnsData() {
        val projectEntity = ProjectFactory.makeProjectEntity()
        val project = ProjectFactory.makeProject()
        stubGetBookmarkedProjects(Observable.just(listOf(projectEntity)))
        stubMapper(project, projectEntity)

        val testObserver = repository.getBookmarkedProjects().test()
        testObserver.assertValue(listOf(project))
    }

    @Test
    fun bookmarkProjectCompletes() {
        stubBookmarkProject(Completable.complete())

        val testObserver = repository.bookmarkProject(DataFactory.randomString()).test()
        testObserver.assertComplete()
    }

    @Test
    fun unbookmarkProjectCompletes() {
        stubUnBookmarkProject(Completable.complete())

        val testObserver = repository.unbookmarkProject(DataFactory.randomString()).test()
        testObserver.assertComplete()
    }

    private fun stubBookmarkProject(completable: Completable) {
        whenever(store.setProjectAsBookmarked(any()))
            .thenReturn(completable)
    }

    private fun stubUnBookmarkProject(completable: Completable) {
        whenever(store.setProjectAsNotBookmarked(any()))
            .thenReturn(completable)
    }

    private fun stubIsCacheExpired(single: Single<Boolean>) {
        whenever(cache.isProjectsCacheExpired())
            .thenReturn(single)
    }

    private fun stubAreProjectsCached(single: Single<Boolean>) {
        whenever(cache.areProjectsCached())
            .thenReturn(single)
    }

    private fun stubMapper(model: Project, entity: ProjectEntity) {
        whenever(mapper.mapFromEntity(entity))
            .thenReturn(model)
    }

    private fun stubGetProjects(observable: Observable<List<ProjectEntity>>) {
        whenever(store.getProjects())
            .thenReturn(observable)
    }

    private fun stubGetBookmarkedProjects(observable: Observable<List<ProjectEntity>>) {
        whenever(store.getBookmarkedProjects())
            .thenReturn(observable)
    }

    private fun stubFactoryGetDataStore() {
        whenever(factory.getDataStore(any(), any()))
            .thenReturn(store)
    }

    private fun stubFactoryGetCacheDataStore() {
        whenever(factory.getCacheDataStore())
            .thenReturn(store)
    }

    private fun stubSaveProjects(completable: Completable) {
        whenever(store.saveProjects(any()))
            .thenReturn(completable)
    }
}